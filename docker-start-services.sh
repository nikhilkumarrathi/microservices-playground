# File the current Host and set it as a ENV variable
# This host will be supplied to all Docker Containers to resolve external Ports
export DOCKERHOST=$(ifconfig | grep -E "([0-9]{1,3}\.){3}[0-9]{1,3}" | grep -v 127.0.0.1 | awk '{ print $2 }' | cut -f2 -d: | head -n1)
echo $DOCKERHOST
docker-compose up -d