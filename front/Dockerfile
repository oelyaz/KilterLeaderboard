# Stage 1: Build the SvelteKit frontend
FROM node:18 AS builder
WORKDIR /app

# Copy package files first to leverage Docker cache
COPY package.json package-lock.json ./

# Install dependencies
RUN rm -rf node_modules
RUN npm install

# Copy the rest of the frontend files
COPY . .

# Build the frontend
RUN npm run build

# Stage 2: Serve the frontend
FROM node:18-alpine
WORKDIR /app

# Copy the built frontend from the builder stage
COPY --from=builder /app/build /app/build
COPY --from=builder /app/package.json /app/package-lock.json ./

# Install production dependencies
RUN npm install --production

# Expose the port the app runs on
EXPOSE 3000

# Command to run the application
CMD ["node", "build"]