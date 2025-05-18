FROM elasticsearch:8.9.1

USER root

RUN mkdir -p /tmp/plugins && \
    chown -R elasticsearch:elasticsearch /tmp/plugins

USER elasticsearch

COPY --chown=elasticsearch:elasticsearch plugins/ /tmp/plugins/

RUN mkdir -p /usr/share/elasticsearch/config/synonyms && \
    for file in /tmp/plugins/*.zip; do \
      elasticsearch-plugin install --batch "file://$file"; \
    done && \
    rm -rf /tmp/plugins

COPY --chown=elasticsearch:elasticsearch config/synonyms/ /usr/share/elasticsearch/config/synonyms/
