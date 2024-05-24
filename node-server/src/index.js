// node-server/src/index.js
const express = require("express");
const axios = require("axios");

const app = express();

const JAVA_BACKEND_URL = "http://localhost:8080/api/hello";

// Route to fetch data from Java backend and log to console
app.get("/fetch-and-log", async (req, res) => {
  try {
    const response = await axios.get(JAVA_BACKEND_URL);
    console.log("Response from Java backend:", response.data);
    res.send("Data fetched and logged to console.");
  } catch (error) {
    console.error("Error fetching data from Java backend:", error);
    res.status(500).send("Failed to fetch data from backend.");
  }
});

app.listen(5000, () => {
  console.log("Node.js server running on http://localhost:5000");
});
