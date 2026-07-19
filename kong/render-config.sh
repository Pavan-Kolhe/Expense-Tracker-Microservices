#!/bin/sh
# Substitutes __JWT_SECRET__ in kong.yml.template with the real JWT_SECRET
# env var, writing the result to /kong-config/kong.yml (a shared volume).
# Plain sed instead of envsubst -> no extra packages needed in the container.
set -e

if [ -z "$JWT_SECRET" ]; then
  echo "ERROR: JWT_SECRET env var is not set" >&2
  exit 1
fi

# Base64 alphabet never contains '&' or '|', so '|' is a safe sed delimiter.
sed "s|__JWT_SECRET__|${JWT_SECRET}|g" /kong-template/kong.yml.template > /kong-config/kong.yml

echo "Kong declarative config rendered to /kong-config/kong.yml"