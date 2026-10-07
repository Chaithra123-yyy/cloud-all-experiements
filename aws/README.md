# cloud-computing
# cloud-computing# ☁️ CLOUDS Association Portal - AWS EC2 Deployment

Official web portal for the **CLOUDS Association** (Computer Science & Engineering Department) at **Sahyadri College of Engineering and Management (SCEM)**, hosted live on an **AWS EC2 Public Instance**.

---

## 🏛️ Institution & Department Overview

### **Institution Directives (SCEM)**
* **Vision:** To be a premier institution in Technology and Management by fostering excellence in education, innovation, incubation, and values to inspire and empower the young minds.
* **Mission:** 
  - Creating an academic ambience to impart holistic education focusing on individual growth, integrity, ethical values, and social responsibility.
  - Develop skill-based learning through industry-institution interaction to enhance competency and promote entrepreneurship.
  - Fostering innovation and creativity through competitive environment with state-of-the-art infrastructure.

### **CSE Department Framework**
* **Vision:** To be a globally recognized center for imparting quality technical education through innovative research and incubation with moral values in the field of computer science and engineering.
* **Mission:**
  - Fostering effective education through internal collaboration with industries to address societal demands.
  - Empowering professionals with strong ethical values and leadership abilities.
  - Developing research and entrepreneurship culture in students and faculty to cultivate lifelong learning.

---

## 💻 AWS EC2 Server Deployment Commands

The web application was hosted publicly using an Amazon Linux AWS EC2 instance running Apache Web Server. The following terminal commands were executed:

```bash
# 1. Update system packages
sudo yum update -y

# 2. Install Apache Web Server
sudo yum install httpd -y

# 3. Enable and start Apache automatically on server boot
sudo systemctl enable --now httpd

# 4. Create and edit the website entry file
nano index.html

# 5. Verify the code written inside index.html
cat index.html

# 6. Copy the HTML file to Apache's public root web directory
sudo cp index.html /var/www/html/
