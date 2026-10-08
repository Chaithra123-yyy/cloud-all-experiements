# 2. C Compiler in a Virtual Machine

Install a C compiler inside an Ubuntu virtual machine and execute a simple C program to check whether a number is even or odd.

**Virtualization – Tutorial 2 of 11**

---

## Aim

To install and use a C compiler inside the virtual machine created using VirtualBox and execute a simple C program to determine whether a given number is even or odd.

---

## Software / Tools Required

* Oracle VirtualBox
* Ubuntu Virtual Machine
* GCC C Compiler
* Gedit Text Editor
* Terminal

---

# Step 1: Import the Ubuntu Virtual Machine

1. Open **VirtualBox**.
2. Go to:

```text
File → Import Appliance
```

3. Browse and select the provided:

```text
ubuntu_gt6.ova
```

4. Import the virtual machine.
5. After importing, open **Settings**.
6. Select **USB**.
7. Choose:

```text
USB 1.1
```

8. Start the `ubuntu_gt6` virtual machine.

---

# Step 2: Open the Ubuntu Terminal

After Ubuntu starts:

1. Log in to the Ubuntu virtual machine.
2. Open the **Terminal**.
3. Navigate to the required directory:

```bash
cd /opt/axis2/axis2-1.7.3/bin
```

Press **Enter**.

---

# Step 3: Create the C Source File

Create a C source file named `first.c` using Gedit:

```bash
gedit first.c
```

Gedit will open a new file.

---

# Step 4: Write the C Program

Enter the following program in `first.c`:

```c
#include<stdio.h>
#include<conio.h>

void main()
{
    int a;

    clrscr();

    printf("Enter the number to find Even Or Not");
    scanf("%d", &a);

    if(a % 2 == 0)
        printf("The Entered number is Even");
    else
        printf("The Entered number is Odd");
}
```

Save the file as:

```text
first.c
```

---

# Step 5: Compile the C Program

Open the terminal and make sure you are in the directory containing `first.c`.

Compile the program using:

```bash
gcc first.c
```

If there are no compilation errors, an executable file named `a.out` will be generated.

---

# Step 6: Run the Program

Run the compiled program using:

```bash
./a.out
```

The program will ask the user to enter a number.

Example:

```text
Enter the number to find Even Or Not
10
The Entered number is Even
```

For an odd number:

```text
Enter the number to find Even Or Not
7
The Entered number is Odd
```

---

# Step 7: Observe the Output

The program checks the entered number using the modulus operator:

```c
a % 2
```

* If the remainder is `0`, the number is **Even**.
* Otherwise, the number is **Odd**.

---

# Program Logic

```text
Start
  |
  v
Enter a number
  |
  v
Calculate number % 2
  |
  +-------- 0 --------+
  |                   |
  v                   v
Even                  Odd
  |                   |
  +---------+---------+
            |
            v
           End
```

---

# Important Commands

### Navigate to the directory

```bash
cd /opt/axis2/axis2-1.7.3/bin
```

### Create the C file

```bash
gedit first.c
```

### Compile

```bash
gcc first.c
```

### Run

```bash
./a.out
```

---

# Expected Result

The C program is successfully compiled and executed inside the Ubuntu virtual machine.

For an even number, the output is:

```text
The Entered number is Even
```

For an odd number, the output is:

```text
The Entered number is Odd
```

---

# Result

Successfully installed and used the C compiler in the Ubuntu virtual machine and executed a C program to determine whether a given number is even or odd.

---

# Conclusion

A C compiler was successfully used inside an Ubuntu virtual machine. A simple C program was created, compiled using GCC, and executed to check whether the entered number is even or odd.

---

# Tutorial 2 of 11

**Topic:** C Compiler in a Virtual Machine
**Status:** Completed
