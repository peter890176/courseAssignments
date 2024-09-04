# Use the official Node.js image as the base image
FROM node:16

# Set the working directory
WORKDIR /app

# Copy package.json and package-lock.json (if they exist) to the working directory
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the project files to the working directory
COPY . .

# Specify the port number the container should expose, which should correspond to the port used by the npm start development server
EXPOSE 3000

# Define the command that will run when the container starts
CMD ["npm", "start"]
