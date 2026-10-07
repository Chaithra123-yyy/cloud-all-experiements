# Salesforce Apex Mailing Service

## About Salesforce

Salesforce is a cloud-based Customer Relationship Management (CRM) platform used by organizations to manage customer information, sales, service, marketing, and business processes.

Salesforce provides **Apex**, a Java-like programming language used to write custom business logic and automate processes on the Salesforce platform.

## About This Project

This project demonstrates how to send an email using **Apex** and Salesforce's `Messaging.SingleEmailMessage` class.

The project contains an Apex class called `MailingService`, which accepts the recipient email address, subject, and message body and sends the email using Salesforce's email service.

## Technologies Used

- Salesforce
- Apex
- Salesforce Developer Console

## How to Execute

### 1. Open Salesforce

Log in to your Salesforce Developer Org.

### 2. Open Developer Console

Click the **Gear icon ⚙️ → Developer Console**.

### 3. Create the Apex Class

Go to:

**File → New → Apex Class**

Enter:

```text
MailingService
