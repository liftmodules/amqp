#!/bin/bash

if [ "${TRAVIS_PULL_REQUEST}" = "false" ]; then
  echo "Not a pull request"
  if [ "${TRAVIS_BRANCH}" = "feature/lift-3-scala-2.12" ]; then
    echo "Attemping publish"
    mkdir -p ~/.sbt/0.13/plugins/
    sbt +publish
  fi
fi
