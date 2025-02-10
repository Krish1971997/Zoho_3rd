package fileManagementSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManagementSystem {

	// Store the current working directory
	private static File currentDirectory = new File(System.getProperty("user.dir"));

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String command;

		// Display the current working directory and wait for input
		while (true) {
			System.out.print(currentDirectory.getAbsolutePath() + " > ");
			command = scanner.nextLine().trim();

			// Split command and arguments
			String[] splitCommand = command.split(" ", 2);
			String mainCommand = splitCommand[0];

			switch (mainCommand) {
			case "cd":
				if (splitCommand.length > 1) {
					changeDirectory(splitCommand[1]);
				} else {
					System.out.println("Usage: cd <directory>");
				}
				break;
			case "mkdir":
				if (splitCommand.length > 1) {
					createDirectory(splitCommand[1]);
				} else {
					System.out.println("Usage: mkdir <directory_name>");
				}
				break;
			case "touch":
				if (splitCommand.length > 1) {
					createFile(splitCommand[1]);
				} else {
					System.out.println("Usage: touch <file_name>");
				}
				break;
			case "pwd":
				printWorkingDirectory();
				break;
			case "delete":
				if (splitCommand.length > 1) {
					deleteFileOrDirectory(splitCommand[1]);
				} else {
					System.out.println("Usage: delete <file/directory>");
				}
				break;
			case "write":
				if (splitCommand.length > 1) {
					writeFile(splitCommand[1]);
				} else {
					System.out.println("Usage: write <file_name>");
				}
				break;
			case "list":
				listFiles();
				break;
			case "exit":
				System.out.println("Exiting File Management System.");
				scanner.close();
				System.exit(0);
			default:
				System.out.println("Unknown command: " + mainCommand);
				break;
			}
		}
	}

	// Command: cd
	private static void changeDirectory(String path) {
		File newDir = new File(currentDirectory, path);
		if (newDir.exists() && newDir.isDirectory()) {
			currentDirectory = newDir;
		} else {
			System.out.println("Directory does not exist.");
		}
	}

	// Command: mkdir
	private static void createDirectory(String dirName) {
		File newDir = new File(currentDirectory, dirName);
		if (newDir.exists()) {
			System.out.println("Directory already exists.");
		} else {
			if (newDir.mkdir()) {
				System.out.println("Directory created successfully.");
			} else {
				System.out.println("Failed to create directory.");
			}
		}
	}

	// Command: touch
	private static void createFile(String fileName) {
		File newFile = new File(currentDirectory, fileName);
		try {
			if (newFile.createNewFile()) {
				System.out.println("File created successfully.");
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("Error creating file.");
		}
	}

	// Command: pwd
	private static void printWorkingDirectory() {
		System.out.println("Current Directory: " + currentDirectory.getAbsolutePath());
	}

	// Command: delete
	private static void deleteFileOrDirectory(String name) {
		File target = new File(currentDirectory, name);
		if (target.exists()) {
			if (target.isDirectory()) {
				if (target.list().length > 0) {
					System.out.println("Directory is not empty.");
				} else {
					target.delete();
					System.out.println("Directory deleted.");
				}
			} else {
				target.delete();
				System.out.println("File deleted.");
			}
		} else {
			System.out.println("File/Directory does not exist.");
		}
	}

	// Command: write
	private static void writeFile(String fileName) {
		File target = new File(currentDirectory, fileName);
		if (!target.exists() || target.isDirectory()) {
			System.out.println("File does not exist or is a directory.");
			return;
		}
		System.out.println("Enter text to write to the file (type 'exit' to stop):");
		Scanner scanner = new Scanner(System.in);
		StringBuilder content = new StringBuilder();
		String line;
		while (!(line = scanner.nextLine()).equals("exit")) {
			content.append(line).append("\n");
		}
		try (FileWriter writer = new FileWriter(target, true)) {
			writer.write(content.toString());
			System.out.println("Content written to file.");
		} catch (IOException e) {
			System.out.println("Error writing to file.");
		}
	}

	// Command: list
	private static void listFiles() {
		File[] files = currentDirectory.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					System.out.println("[DIR] " + file.getName());
				} else {
					System.out.println(file.getName());
				}
			}
		} else {
			System.out.println("Failed to list files.");
		}
	}
}