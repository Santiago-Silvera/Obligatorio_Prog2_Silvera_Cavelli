const express = require("express");
const path = require("path");
const axios = require("axios");

const app = express();
const PORT = 5000;
const JAVA_BACKEND_URL = "http://localhost:8080/api";

// Serve static files from the src directory
app.use(express.static(path.join(__dirname)));

// Handle button 1 click
app.get("/api/button1", async (req, res) => {
  try {
    const response = await axios.get(`${JAVA_BACKEND_URL}/button1`);
    res.send(`Button 1 clicked: ${response.data}`);
  } catch (error) {
    console.error("Error:", error);
    res.status(500).send("Failed to handle button 1 click");
  }
});

// Handle button 2 click
app.get("/api/button2", async (req, res) => {
  try {
    const response = await axios.get(`${JAVA_BACKEND_URL}/button2`);
    res.send(`Button 2 clicked: ${response.data}`);
  } catch (error) {
    console.error("Error:", error);
    res.status(500).send("Failed to handle button 2 click");
  }
});

// Handle button 3 click
app.get("/api/button3", async (req, res) => {
  try {
    const response = await axios.get(`${JAVA_BACKEND_URL}/button3`);
    res.send(`Button 3 clicked: ${response.data}`);
  } catch (error) {
    console.error("Error:", error);
    res.status(500).send("Failed to handle button 3 click");
  }
});

// Handle button 4 click
app.get("/api/button4", async (req, res) => {
  try {
    const response = await axios.get(`${JAVA_BACKEND_URL}/button4`);
    res.send(`Button 4 clicked: ${response.data}`);
  } catch (error) {
    console.error("Error:", error);
    res.status(500).send("Failed to handle button 4 click");
  }
});

// Handle button 5 click
app.get("/api/button5", async (req, res) => {
  try {
    const response = await axios.get(`${JAVA_BACKEND_URL}/button5`);
    res.send(`Button 5 clicked: ${response.data}`);
  } catch (error) {
    console.error("Error:", error);
    res.status(500).send("Failed to handle button 5 click");
  }
});

app.listen(PORT, () => {
  console.log(`Node.js server running on http://localhost:${PORT}`);
});
