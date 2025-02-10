#!/bin/bash

MONITOR_NAME=$(basename "$0")
MONITOR_PID_FILE="/tmp/${MONITOR_NAME}.pid"

write_csv() {
  TIMESTAMP=$(date +"%Y-%m-%d %H:%M:%S")
  CURRENT_DATE=$(date +"%Y-%m-%d")
  CSV_FILE="disk_usage_${CURRENT_DATE}.csv"

  if [ ! -f "$CSV_FILE" ]; then
    echo "Timestamp,Disk_Usage,Inode_Usage" > "$CSV_FILE"
  fi

  DISK_USAGE=$(df / | tail -1 | awk '{print $5}')
  INODE_USAGE=$(df -i / | tail -1 | awk '{print $5}')
  echo "$TIMESTAMP,$DISK_USAGE,$INODE_USAGE" >> "$CSV_FILE"
}

monitor_disk_usage() {
  while true; do
    write_csv
    sleep 60
  done
}

check_params() {
  if [ $# -ne 1 ]; then
    echo "Usage: $0 {START|STOP|STATUS}"
    exit 1
  fi
}

start_monitoring() {
  if [ -f "$MONITOR_PID_FILE" ]; then
    echo "Monitoring is already running with PID $(cat "$MONITOR_PID_FILE")"
    exit 1
  fi

  monitor_disk_usage &
  PID=$!
  echo "$PID" > "$MONITOR_PID_FILE"
  echo "Monitoring started with PID $PID"
}

stop_monitoring() {
  if [ ! -f "$MONITOR_PID_FILE" ]; then
    echo "Monitoring is not running."
    exit 1
  fi

  PID=$(cat "$MONITOR_PID_FILE")
  kill "$PID" && rm -f "$MONITOR_PID_FILE"
  echo "Monitoring stopped."
}

status_monitoring() {
  if [ -f "$MONITOR_PID_FILE" ]; then
    echo "Monitoring is running with PID $(cat "$MONITOR_PID_FILE")"
  else
    echo "Monitoring is not running."
  fi
}

check_params "$@"

case "$1" in
  START)
    start_monitoring
    ;;
  STOP)
    stop_monitoring
    ;;
  STATUS)
    status_monitoring
    ;;
  *)
    echo "Usage: $0 {START|STOP|STATUS}"
    exit 1
    ;;
esac

exit 0
