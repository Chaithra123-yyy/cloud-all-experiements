# 10. Static Website on S3 + CloudFront

Host a static website on Amazon S3 and deliver it through Amazon CloudFront.

**AWS S3 / CloudFront – Tutorial 10 of 11**

---

## Aim

To deploy a static website using Amazon S3 and configure Amazon CloudFront to deliver the website securely over HTTPS.

---

## AWS Services Used

* Amazon S3
* Amazon CloudFront

---

# Step 1: Create the S3 Bucket

Amazon S3 is used to store the static website files.

### 1. Open Amazon S3

1. Log in to the AWS Management Console.
2. Search for **S3**.
3. Open the **Amazon S3** service.
4. Click **Create bucket**.

### 2. Configure the Bucket

Enter the following details:

* **Bucket name:** Enter a globally unique bucket name.

  Example:

  `my-static-website-2026`

* **AWS Region:** Select your preferred region.

### 3. Disable Block Public Access

Under **Block Public Access settings for this bucket**:

* Uncheck **Block all public access**.
* Confirm the warning.

This is required for the public S3 static website used in this lab.

> **Security Note:** Making an S3 bucket public is suitable only for a simple lab/demo. For production websites, a private S3 bucket with CloudFront Origin Access Control (OAC) is recommended.

### 4. Create the Bucket

Leave the remaining settings as default and click:

**Create bucket**

---

# Step 2: Enable Static Website Hosting

1. Open the S3 bucket.

2. Go to the **Properties** tab.

3. Scroll down to **Static website hosting**.

4. Click **Edit**.

5. Select **Enable**.

6. Set the following:

   * **Index document:** `index.html`
   * **Error document:** `error.html` (optional)

7. Click **Save changes**.

The bucket can now serve the uploaded HTML files as a static website.

---

# Step 3: Configure the S3 Bucket Policy

The website files need public read permission so that the S3 static website endpoint can serve them.

1. Open the S3 bucket.
2. Go to the **Permissions** tab.
3. Find **Bucket policy**.
4. Click **Edit**.
5. Add the following policy.
6. Replace `BUCKET_NAME` with your actual bucket name.
7. Click **Save changes**.

### Bucket Policy

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "PublicReadGetObject",
            "Effect": "Allow",
            "Principal": "*",
            "Action": "s3:GetObject",
            "Resource": "arn:aws:s3:::BUCKET_NAME/*"
        }
    ]
}
```

> **Important:** Use `s3:GetObject` instead of `s3:*`. The `s3:*` permission can allow unwanted write and delete operations and should not be used for a public website.

---

# Step 4: Upload Website Files

1. Open the S3 bucket.
2. Click **Upload**.
3. Click **Add files**.
4. Select your website files.

Example:

```text
index.html
styles.css
script.js
images/
```

5. Click **Upload**.

Make sure `index.html` is present in the root of the bucket.

---

# Step 5: Test the Website Using S3

1. Go to the **Properties** tab.
2. Scroll to **Static website hosting**.
3. Find the **Bucket website endpoint**.
4. Open the URL in a browser.

The website should load successfully.

### Expected Result

The static website is now accessible using the S3 website endpoint.

Example:

```text
http://BUCKET_NAME.s3-website-REGION.amazonaws.com
```

---

# Step 6: Create a CloudFront Distribution

Amazon CloudFront is used to deliver the website through a CDN and HTTPS.

### 1. Open CloudFront

1. Open the AWS Management Console.
2. Search for **CloudFront**.
3. Open **Amazon CloudFront**.
4. Click **Create distribution**.

---

# Step 7: Configure the CloudFront Origin

For the **Origin domain**:

1. Copy the S3 static website endpoint from the S3 bucket.
2. Enter the endpoint as the CloudFront origin.
3. Remove `http://` from the beginning.

Example:

```text
my-static-website-2026.s3-website-us-east-1.amazonaws.com
```

> **Important:** When using the S3 static website endpoint, configure it as a custom origin. Do not select the S3 REST endpoint for this particular lab setup.

---

# Step 8: Configure CloudFront Settings

Continue through the CloudFront configuration.

### Viewer Protocol Policy

Set:

**Redirect HTTP to HTTPS**

This automatically redirects HTTP requests to HTTPS.

### Allowed HTTP Methods

For a static website, use:

```text
GET, HEAD
```

These methods are sufficient for retrieving website files.

### Cache Policy

Use the recommended caching policy:

**CachingOptimized**

This allows CloudFront to cache static website content and improve loading speed.

---

# Step 9: Create the CloudFront Distribution

1. Review the CloudFront settings.
2. Click **Create distribution**.
3. Wait for the distribution to finish deploying.

CloudFront deployment may take several minutes.

---

# Step 10: Get the CloudFront Domain Name

After deployment:

1. Open the CloudFront distribution.
2. Find **Distribution domain name**.

Example:

```text
d111111abcdef8.cloudfront.net
```

---

# Step 11: Test the Website Through CloudFront

Open the CloudFront domain in a browser.

Example:

```text
https://d111111abcdef8.cloudfront.net
```

### Expected Result

The static website should load successfully through CloudFront.

The request flow is:

```text
User / Browser
      |
      v
  CloudFront
      |
      v
  Amazon S3
      |
      v
Website Files
index.html
styles.css
script.js
```

---

# Step 12: Verify HTTPS

Open the CloudFront URL using:

```text
https://d111111abcdef8.cloudfront.net
```

The website should load using HTTPS.

CloudFront provides HTTPS access to the website even though the S3 static website endpoint itself uses HTTP.

---

# Screenshots

### 1. Creating the S3 Bucket

![Creating the S3 bucket](https://cloud-beige-eight.vercel.app/assets/img/exp10/01-create-bucket.png)

### 2. Enabling Static Website Hosting

![Enabling static website hosting](https://cloud-beige-eight.vercel.app/assets/img/exp10/02-static-website-hosting.png)

### 3. Uploading Website Files

![Uploading website files](https://cloud-beige-eight.vercel.app/assets/img/exp10/03-upload-files.png)

### 4. Website Served from S3

![Static site served from S3](https://cloud-beige-eight.vercel.app/assets/img/exp10/04-s3-static-url.png)

### 5. CloudFront Origin Configuration

![Setting CloudFront origin](https://cloud-beige-eight.vercel.app/assets/img/exp10/05-cloudfront-origin-domain.png)

### 6. CloudFront Distribution Settings

![CloudFront distribution settings](https://cloud-beige-eight.vercel.app/assets/img/exp10/06-cloudfront-settings.png)

### 7. Redirect HTTP to HTTPS

![Redirect HTTP to HTTPS](https://cloud-beige-eight.vercel.app/assets/img/exp10/07-redirect-http-https.png)

### 8. CloudFront Distribution Created

![Distribution created](https://cloud-beige-eight.vercel.app/assets/img/exp10/08-distribution-created.png)

### 9. Final Website

![Final website through CloudFront](https://cloud-beige-eight.vercel.app/assets/img/exp10/09-final-website-access.png)

---

# Security Notes

For this lab, the S3 bucket is configured for public access because the S3 static website endpoint is being used as the CloudFront origin.

For a production deployment, a better architecture is:

```text
User
  |
  v
HTTPS
  |
  v
CloudFront
  |
  | Origin Access Control (OAC)
  v
Private S3 Bucket
```

In a production setup:

* Keep **Block all public access** enabled.
* Do not make the S3 bucket public.
* Use **CloudFront Origin Access Control (OAC)**.
* Allow CloudFront to access S3 objects through a bucket policy.
* Serve the website through HTTPS.

---

# Result

Successfully deployed a static website using Amazon S3 and Amazon CloudFront.

The website files were stored in Amazon S3 and delivered to users through CloudFront using HTTPS.

---

# Conclusion

A static website was successfully hosted on Amazon S3 and integrated with Amazon CloudFront. CloudFront provides CDN-based content delivery and HTTPS access, improving the performance and security of the website.

---

# AWS Services

**Amazon S3** – Stores the static website files.

**Amazon CloudFront** – Delivers the website through a global CDN and HTTPS.

---

# Tutorial 10 of 11

**Topic:** Static Website on S3 + CloudFront
**Status:** Completed
