# 11. Video Streaming with S3 + CloudFront

## Aim

To create a private video streaming service using Amazon S3 and Amazon CloudFront. The video files are stored in a private S3 bucket and are securely delivered to users through CloudFront using Origin Access Control (OAC).

---

## AWS Services Used

* Amazon S3
* Amazon CloudFront
* Origin Access Control (OAC)

> Note: AWS Elemental MediaConvert can be used for video transcoding and adaptive streaming, but it is not required for this basic S3 + CloudFront lab.

---

# Step 1: Create an S3 Bucket

Amazon S3 is used to store the video files securely.

### 1. Open Amazon S3

1. Log in to the AWS Management Console.
2. Search for **S3**.
3. Open the **Amazon S3** service.
4. Click **Create bucket**.

### 2. Configure the Bucket

Enter the following details:

* **Bucket name:** Use a globally unique name.

  Example:
  `my-video-streaming-bucket-2026`

* **AWS Region:** Select your preferred region.

  Example:
  `us-east-1`

### 3. Configure Public Access

Under **Block Public Access settings for this bucket**:

* Keep **Block all public access** enabled.

The bucket must remain private because users should access the video through CloudFront.

### 4. Enable Versioning

Under **Bucket Versioning**:

* Select **Enable**.

### 5. Enable Encryption

Under **Default encryption**:

* Select **Enable**
* Choose **SSE-S3**

### 6. Create the Bucket

Click:

**Create bucket**

---

# Step 2: Upload a Video

1. Open the newly created S3 bucket.
2. Click **Upload**.
3. Click **Add files**.
4. Select a test video file.

Example:

`sample.mp4`

5. Click **Upload**.

The video should now appear inside the S3 bucket.

---

# Step 3: Create a CloudFront Distribution

Amazon CloudFront works as the CDN and securely retrieves the private video from S3.

### 1. Open CloudFront

1. Open the AWS Management Console.
2. Search for **CloudFront**.
3. Open **Amazon CloudFront**.
4. Click **Create distribution**.

### 2. Configure the Origin

For **Origin domain**:

* Select the S3 bucket created in Step 1.

Example:

`my-video-streaming-bucket-2026.s3.amazonaws.com`

### 3. Configure Origin Access

Under **Origin access**:

* Select **Origin access control settings (recommended)**.
* Create a new Origin Access Control (OAC).
* Keep the default settings.
* Save the OAC.

OAC allows CloudFront to access objects in the private S3 bucket without making the bucket public.

### 4. Configure Viewer Protocol Policy

Set:

**Viewer protocol policy → Redirect HTTP to HTTPS**

This automatically redirects HTTP requests to HTTPS.

### 5. Configure HTTP Methods

Set **Allowed HTTP methods** to:

`GET, HEAD`

These methods are sufficient for retrieving video files.

### 6. Configure Cache Policy

Select:

**CachingOptimized**

This allows CloudFront to cache the video and improve delivery performance.

### 7. Configure WAF

For this lab:

**Do not enable security protections**

WAF is not required for this basic demonstration.

### 8. Create the Distribution

Review the configuration and click:

**Create distribution**

---

# Step 4: Update the S3 Bucket Policy

The S3 bucket is private, so CloudFront needs permission to retrieve objects from it.

After creating the CloudFront distribution, AWS may display a message asking you to update the S3 bucket policy.

### 1. Copy the Policy

Copy the bucket policy provided by CloudFront.

An example policy is:

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "AllowCloudFrontServicePrincipal",
            "Effect": "Allow",
            "Principal": {
                "Service": "cloudfront.amazonaws.com"
            },
            "Action": "s3:GetObject",
            "Resource": "arn:aws:s3:::my-video-streaming-bucket-2026/*"
        }
    ]
}
```

> Replace `my-video-streaming-bucket-2026` with your actual S3 bucket name.

### 2. Open S3 Permissions

1. Go to **S3**.
2. Open your video bucket.
3. Select the **Permissions** tab.
4. Find **Bucket policy**.
5. Click **Edit**.
6. Paste the CloudFront bucket policy.
7. Click **Save changes**.

The policy allows CloudFront to read objects from the bucket while keeping the bucket itself private.

---

# Step 5: Wait for CloudFront Deployment

After creating the distribution:

1. Open the CloudFront console.
2. Select your distribution.
3. Check the **Last modified / Status** information.
4. Wait until the distribution finishes deploying.

CloudFront may take several minutes to become fully available.

---

# Step 6: Get the CloudFront Domain Name

1. Open your CloudFront distribution.
2. Find **Distribution domain name**.

Example:

`d111111abcdef8.cloudfront.net`

---

# Step 7: Test the Video Stream

To access the uploaded video, add the video filename after the CloudFront domain.

For example:

```text
https://d111111abcdef8.cloudfront.net/sample.mp4
```

Replace:

* `d111111abcdef8.cloudfront.net` with your CloudFront domain.
* `sample.mp4` with your uploaded video filename.

Open the URL in a web browser.

### Expected Result

The video should load through CloudFront.

The user accesses:

```text
Browser
   |
   v
CloudFront
   |
   v
Private S3 Bucket
   |
   v
Video File
```

The S3 bucket remains private, and CloudFront retrieves the video using Origin Access Control.

---

# Step 8: Verify S3 Privacy

To verify that the bucket is private:

1. Copy the direct S3 object URL.
2. Try opening it in a browser without authentication.

Example:

```text
https://my-video-streaming-bucket-2026.s3.amazonaws.com/sample.mp4
```

The direct S3 URL should not provide public access.

The video should instead be accessed through the CloudFront URL:

```text
https://d111111abcdef8.cloudfront.net/sample.mp4
```

---

# Architecture

```text
                    User / Browser
                           |
                           |
                           v
                  +----------------+
                  |   CloudFront   |
                  |      CDN       |
                  +----------------+
                           |
                    Origin Access
                      Control
                           |
                           v
                  +----------------+
                  |   Amazon S3    |
                  |  Private       |
                  |    Bucket      |
                  +----------------+
                           |
                           v
                     Video File
                      sample.mp4
```

---

# Important Security Settings

Make sure the following settings are enabled:

* S3 bucket is private.
* Block all public access is enabled.
* CloudFront Origin Access Control (OAC) is configured.
* S3 bucket policy allows CloudFront to read objects.
* Viewer protocol policy is set to HTTPS.
* Direct public access to S3 objects is not allowed.

---

# Result

Successfully created a private video streaming setup using:

* Amazon S3 for secure video storage.
* Amazon CloudFront for CDN-based video delivery.
* Origin Access Control (OAC) for secure communication between CloudFront and S3.

The video can be accessed through the CloudFront distribution while the S3 bucket remains private.

---

# Conclusion

A secure video streaming architecture was successfully implemented using Amazon S3 and Amazon CloudFront. The video is stored in a private S3 bucket and delivered through CloudFront using Origin Access Control. This prevents direct public access to the S3 objects while allowing users to access the video through the CDN.

---

# AWS Services

Amazon S3
Amazon CloudFront
Origin Access Control (OAC)

---

# Tutorial 11 of 11

**Topic:** Video Streaming with S3 + CloudFront
**Status:** Completed
