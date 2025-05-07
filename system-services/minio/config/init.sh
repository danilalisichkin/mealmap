#!/bin/sh

ENDPOINT="${MINIO_ENDPOINT:-http://localhost:9000}"
MINIO_USER="${MINIO_ROOT_USER:-minioadmin}"
MINIO_PASSWORD="${MINIO_ROOT_PASSWORD:-minioadmin}"
BUCKETS="${MINIO_BUCKETS:-default}"

echo "⏳ Ожидание запуска MinIO..."
sleep 5

echo "🔐 Добавление конфигурации для mc..."
mc config host add myminio "$ENDPOINT" "$MINIO_USER" "$MINIO_PASSWORD"

echo "📦 Создание бакетов: $BUCKETS"
IFS=','

for bucket in $BUCKETS; do
  mc mb -p "myminio/$bucket" || echo "⚠️ Бакет '$bucket' уже существует"
done

echo "✅ Все бакеты инициализированы"
exit 0
