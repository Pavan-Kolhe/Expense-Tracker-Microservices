FROM python:3.11.4-slim

WORKDIR /app

# System deps needed for building some python wheels + curl for healthcheck
RUN apt-get update && apt-get install -y --no-install-recommends \
    build-essential curl \
    && rm -rf /var/lib/apt/lists/*

# Copy dependency manifests first (better layer caching)
COPY requirements.txt setup.py ./
COPY src ./src

# setup.py's install_requires is the authoritative dependency list
# (requirements.txt is missing kafka-python/python-dotenv/jsonpickle),
# so install directly from it — no manual "python setup.py sdist" step needed.
RUN pip install --no-cache-dir .

ENV FLASK_APP=src/app/__init__.py
ENV PYTHONUNBUFFERED=1

EXPOSE 8010

HEALTHCHECK --interval=15s --timeout=5s --start-period=20s --retries=5 \
    CMD curl -f http://localhost:8010/health || exit 1

CMD ["flask", "run", "--host=0.0.0.0", "--port=8010"]