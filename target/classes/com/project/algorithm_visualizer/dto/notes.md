````md id="dto-layer-your-code"
# DTO Layer (Based on Your Project) 

## What is DTO Layer (In Your Project)?

In your project, DTO layer is:

👉 The **data carrier between client and backend**  
👉 It defines **what data comes in and what goes out**

---

## Your DTO Classes

### 1. AlgorithmRequest (Input DTO)

```java
public class AlgorithmRequest
````

👉 Used to **receive data from client**

---

### What it Contains

```java
private List<Integer> numbers;
private Integer target;
```

* `numbers` → array to process
* `target` → used for searching algorithms

---

### Role of Request DTO

* Takes input from frontend (JSON)
* Converts it into Java object
* Sends it to controller → service

---

## 2. AlgorithmResponse (Output DTO)

```java
public class AlgorithmResponse
```

👉 Used to **send data back to client**

---

### What it Contains

```java
private List<SortSteps> reportList;
private int totalSwaps;
private int totalComparision;
private String theoriticalComplexity;
private int theoriticalSteps;
```

---

### Role of Each Field

* `reportList` → full step-by-step visualization
* `totalSwaps` → number of swaps performed
* `totalComparision` → comparisons count
* `theoriticalComplexity` → Big-O (like O(n²))
* `theoriticalSteps` → expected max steps

---

## What DTO Layer is Doing in Your Project

### 1. Handles Input (Request DTO)

```id="dtoflow1"
Client → AlgorithmRequest → Controller
```

👉 User sends JSON → becomes `AlgorithmRequest`

---

### 2. Handles Output (Response DTO)

```id="dtoflow2"
Service → AlgorithmResponse → Controller → Client
```

👉 Service prepares result → sent back as JSON

---

### 3. Acts as Safe Data Format

👉 Only required fields are exposed
👉 No internal logic or unnecessary data

---

## Key Responsibilities (From Your Code)

* 📥 Accept user input (numbers, target)
* 📤 Send processed output (steps, stats)
* 🔄 Transfer data between layers
* 🧹 Keep API structure clean

---

## Important Observations

### 1. DTO ≠ Model

* `SortSteps` → Model (data structure inside app)
* `AlgorithmResponse` → DTO (data sent outside)

👉 DTO can **contain model objects** (like you did)

---

### 2. No Logic Inside DTO

✔ Only fields
✔ Getters/Setters
✔ Constructors

👉 Perfect implementation

---

### 3. Required for JSON Conversion

Spring uses:

* Getters/Setters
* Default constructor

👉 To convert:

* JSON ↔ Java object

---

## Real Understanding

👉 DTO = **“Communication format of your backend”**

* Request DTO → What user sends
* Response DTO → What user receives

---

## Final Flow in Your Project

```id="dtoflow3"
Client → Request DTO → Controller → Service → Response DTO → Client
```

---

## One Line Understanding

👉 DTO = **Defines how data enters and leaves your backend (input + output format)**

