#!/bin/sh
# This script builds one or more service modules in parallel.
#
# USAGE:
#   ./build-all-modules.sh              # Build all modules
#   ./build-all-modules.sh <module>     # Build a single module
#   ./build-all-modules.sh <m1> <m2>... # Build specific modules

build_module() {
  module_name=$1
  echo "--> Building module: ${module_name}"
  case ${module_name} in
  "config-service")
    (cd ./config-service && ./gradlew bootJar -x test)
    ;;
  "api-gateway")
    (cd ./api-gateway && ./gradlew bootJar -x test)
    ;;
  "payment-service")
    (cd ./payment-service && ./gradlew bootJar -x test)
    ;;
  "passenger-service")
    (cd ./passenger-service && ./mvnw clean package -DskipTests)
    ;;
  "eureka-server")
    (cd ./eureka-server && ./mvnw clean package -DskipTests)
    ;;
  "trip-service")
    (cd ./trip-service && ./gradlew bootJar -x test)
    ;;
  *)
    echo "--> ERROR: Unknown module '${module_name}'. Skipping."
    # Return non-zero to indicate failure for this sub-process
    return 1
    ;;
  esac
}

modules_to_build=""
if [ $# -gt 0 ]; then
  echo "Building specified modules: $@"
  modules_to_build="$@"
else
  echo "Building all modules..."
  modules_to_build="config-service api-gateway payment-service passenger-service eureka-server trip-service"
fi

pids=""
for module in ${modules_to_build}; do
  build_module "${module}" &
  pids="${pids} $!"
done

echo
echo "Waiting for all build jobs to complete..."
echo

failures=0
for pid in ${pids}; do
  if ! wait "${pid}"; then
    failures=$((failures + 1))
    echo "--> ERROR: A build job failed (PID: ${pid})."
  fi
done

echo
if [ ${failures} -gt 0 ]; then
  echo "${failures} build(s) failed."
  exit 1
else
  echo "All builds completed successfully."
fi

