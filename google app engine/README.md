# 9. Google App Engine Launcher

Build and run a simple Python web application using Google App Engine Launcher.

**Google App Engine – Tutorial 9 of 11**

---

## Aim

To create and run a simple Python web application using Google App Engine Launcher and view the application locally in a web browser.

---

# Step 1: Create the Application Folder

1. Create a working directory named `apps`.
2. Inside the `apps` directory, create a folder named:

```text
ae-01-trivial
```

The folder structure should look like:

```text
apps/
└── ae-01-trivial/
    ├── app.yaml
    └── index.py
```

---

# Step 2: Create the `app.yaml` File

Inside the `ae-01-trivial` folder, create a file named:

```text
app.yaml
```

Add the following configuration:

```yaml
application: ae-01-trivial
version: 1
runtime: python
api_version: 1

handlers:
- url: /.*
  script: index.py
```

### Explanation

* `application` – Specifies the application name.
* `version` – Specifies the application version.
* `runtime` – Specifies the Python runtime.
* `api_version` – Specifies the App Engine API version.
* `handlers` – Defines how incoming requests are handled.
* `script` – Specifies the Python file that runs when a request is received.

> **Note:** This configuration belongs to the older Google App Engine Python 2 / legacy Launcher environment. Modern Google App Engine uses newer Python runtimes and deployment methods.

---

# Step 3: Create the `index.py` File

Inside the same `ae-01-trivial` folder, create:

```text
index.py
```

Add the following code:

```python
print 'Content-Type: text/plain'
print ' '
print 'Hello there Chuck'
```

This program sends a plain-text response to the browser.

---

# Step 4: Start Google App Engine Launcher

1. Open **GoogleAppEngineLauncher**.
2. From the menu, select:

**File → Add Existing Application**

3. Select the `ae-01-trivial` folder.
4. Click **Add**.

The application should now appear in the Google App Engine Launcher.

---

# Step 5: Run the Application

1. Select the `ae-01-trivial` application.
2. Click **Run**.
3. Wait for the application to start.
4. A green icon should appear when the application is running.

---

# Step 6: Open the Application in a Browser

1. Select the application in the launcher.
2. Click **Browse**.

The application should open at:

```text
http://localhost:8080/
```

### Expected Output

```text
Hello there Chuck
```

The message confirms that the Python web application is running successfully.

---

# Step 7: Modify the Application

To test whether changes are reflected:

1. Open `index.py`.
2. Change the name.

For example:

```python
print 'Content-Type: text/plain'
print ' '
print 'Hello there Ammu'
```

3. Save the file.
4. Go back to the browser.
5. Click **Refresh**.

The updated message should appear.

---

# Step 8: View Application Logs

Google App Engine Launcher provides logs for monitoring application requests.

1. Select the application.
2. Click **Logs**.
3. A log window will appear.
4. Open the application in the browser.
5. Observe the request information in the log.

The logs can be used to check whether requests are reaching the application and to identify errors.

---

# Step 9: Handle Configuration Errors

If there is an error in `app.yaml`, the application may fail to start.

A yellow warning icon may appear next to the application in Google App Engine Launcher.

### Troubleshooting Steps

1. Check the `app.yaml` file carefully.
2. Verify the indentation.
3. Check the application name.
4. Verify that `index.py` exists in the same folder.
5. Open the **Logs** window.
6. Check the error message for details.
7. Correct the configuration.
8. Run the application again.

---

# Step 10: Test the Application Again

After correcting any errors:

1. Stop the application if it is running.
2. Start it again using **Run**.
3. Wait for the green running icon.
4. Click **Browse**.
5. Open:

```text
http://localhost:8080/
```

The application should display the expected greeting.

---

# Screenshots

### 1. Application Added to Launcher

![Application added to the launcher](https://cloud-beige-eight.vercel.app/assets/img/exp09/01-launcher-application-added.png)

### 2. Application Output

![Hello there Chuck in the browser](https://cloud-beige-eight.vercel.app/assets/img/exp09/02-browser-output.png)

### 3. Request Logs

![Request log window](https://cloud-beige-eight.vercel.app/assets/img/exp09/03-log-window.png)

### 4. YAML Configuration Error

![Yellow warning icon for a configuration error](https://cloud-beige-eight.vercel.app/assets/img/exp09/04-yaml-error-icon.png)

### 5. Error Details in Log

![Error detail in the log](https://cloud-beige-eight.vercel.app/assets/img/exp09/05-error-log-detail.png)

---

# Application Structure

```text
apps/
└── ae-01-trivial/
    ├── app.yaml
    └── index.py
```

---

# Expected Result

The Google App Engine application starts successfully in the local development environment and displays:

```text
Hello there Chuck
```

The application can also be modified and refreshed to display updated output.

---

# Result

Successfully created and executed a simple Python web application using Google App Engine Launcher.

The application was accessed locally through:

```text
http://localhost:8080/
```

---

# Conclusion

A simple Python web application was created using Google App Engine Launcher. The application was successfully configured using `app.yaml`, executed using `index.py`, tested through a web browser, and monitored using the application logs.

---

# Technologies Used

* Google App Engine
* Google App Engine Launcher
* Python
* YAML
* Web Browser

---

# Tutorial 9 of 11

**Topic:** Google App Engine Launcher
**Status:** Completed
