const express = require("express");
const morgan = require("morgan");
const connectDB = require("./config/db");
const bodyParser = require("body-parser");
const cors = require("cors");
const http = require("http");

// Config dotev...
require("dotenv").config();

const app = express();

// Connect to database
connectDB();

// body parser
app.use(bodyParser.json());
// Load routes
const authRouter = require("./api/auth.route");

// Dev Logging Middleware
if (process.env.NODE_ENV === "development") {
  app.use(
    cors({
      origin: process.env.CLIENT_URL,
    })
  );
  app.use(morgan("dev"));
}

// Use Routes
app.use("/api", authRouter);

app.use((req, res) => {
  res.status(404).json({
    success: false,
    msg: "Page not found",
  });
});

const PORT = process.env.PORT || 5000;

const server = http.createServer(app);
const SocketServer = require("./socket");
SocketServer(server);

server.listen(PORT, () => {
  console.log(`App listening on port ${PORT}`);
});
