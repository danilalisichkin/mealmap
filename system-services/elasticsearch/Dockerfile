FROM elasticsearch:8.9.1

COPY plugins/ /tmp/plugins/

RUN mkdir -p /usr/share/elasticsearch/config/synonyms && \
    for file in /tmp/plugins/*.zip; do \
      elasticsearch-plugin install --batch "file://$file"; \
    done && \
    rm -rf /tmp/plugins

COPY config/synonyms/ /usr/share/elasticsearch/config/synonyms/
