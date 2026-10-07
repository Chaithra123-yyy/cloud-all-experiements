Azure Thread-Based Image Processing

Aim

To implement a thread-based image processing application using Microsoft Azure Blob Storage and Python.

Technologies

- Microsoft Azure Blob Storage
- Python
- Pillow
- Azure Storage Blob
- Threading

Azure Setup

1. Create a Storage Account in Azure.
2. Create a private Blob Container named:

images

3. Upload sample images into the container.
4. Go to Access Keys and copy the Connection String.

Install Dependencies

pip install azure-storage-blob pillow

Project Structure

Azure-Image-Processing/
├── app.py
└── README.md

Run

Replace:

connection_string = "YOUR_CONNECTION_STRING"

with your Azure Storage connection string.

Then run:

python app.py

Working

- Images are read from the Azure Blob container.
- A separate thread is created for each image.
- Each image is resized to 200 × 200 pixels.
- The processed image is uploaded with the prefix "processed_".

Expected Output

All images processed successfully

Azure container:

image1.jpg
image2.jpg
processed_image1.jpg
processed_image2.jpg

Result

Thus, a thread-based image processing application was successfully implemented using Microsoft Azure Blob Storage and Python multithreading.