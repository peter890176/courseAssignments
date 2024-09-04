# Use the official Node.js image as the base image
FROM node:16

# Set the working directory
WORKDIR /usr/src/app

# Copy package.json and package-lock.json (if they exist)
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy all files into the container
COPY . .

# Expose port 8000 inside the container
EXPOSE 8000

# Start the Node application
CMD node ./controller/destroy.js && node ./controller/init.js && node server.js
