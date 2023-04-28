#!/bin/bash

if test -d ~/.caas; then
    echo "Directory exists"
else
    mkdir ~/.caas
    cp src/test/resources/certificate-key.caas ~/.caas/certificate-key.caas
    export CAAS_CONFIG_PATH /config/certificate-key.caas
fi