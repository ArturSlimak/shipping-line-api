#!/bin/sh
# Maven Wrapper bootstrap script
# Downloads and runs Maven if not already available
set -e

MAVEN_HOME="${HOME}/.m2/wrapper/dists/apache-maven-3.9.6"
MVN_CMD="${MAVEN_HOME}/bin/mvn"

if [ ! -f "${MVN_CMD}" ]; then
  echo "Maven not found. Please install Maven 3.8+ or download the wrapper JAR."
  echo "  brew install maven    # macOS"
  echo "  sudo apt install maven # Ubuntu/Debian"
  exit 1
fi

exec "${MVN_CMD}" "$@"
