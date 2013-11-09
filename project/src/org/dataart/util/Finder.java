package org.dataart.util;
/**
 * Sample code that finds files that match the specified glob pattern.
 * For more information on what constitutes a glob pattern, see
 * http://docs.oracle.com/javase/tutorial/essential/io/fileOps.html#glob
 *
 * The file or directories that match the pattern are printed to
 * standard out.  The number of matches is also printed.
 *
 * When executing this application, you must put the glob pattern
 * in quotes, so the shell will not expand any wild cards:
 *              java Find . -name "*.java"
 */

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.ArrayList;

import static java.nio.file.FileVisitResult.*;


//public static void main(String[] args)
//      throws IOException {
//
//      if (args.length < 3 || !args[1].equals("-name"))
//          usage();
//
//      Path startingDir = Paths.get(args[0]);
//      String pattern = args[2];
//
//      Finder finder = new Finder(pattern);
//      Files.walkFileTree(startingDir, finder);
//      finder.done();
//  }

public class Finder extends SimpleFileVisitor<Path> {

	private final PathMatcher matcher;

	private ArrayList<Path> matchedFiles = new ArrayList<Path>();

	/**
	 * Types to retrieve
	 */
	public static enum FIND_TYPES {BOTH, FILES, DIRS};
	
	private FIND_TYPES findType;
	
	public Finder(String pattern, FIND_TYPES findType) {
		this.findType = findType;
		matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
	}

	// Compares the glob pattern against
	// the file or directory name.
	void find(Path file) {
		Path name = file.getFileName();
		if(name != null && matcher.matches(name)) {
			if(Files.isDirectory(file) && (findType == FIND_TYPES.DIRS || findType == FIND_TYPES.BOTH)) {
				matchedFiles.add(file);				
			} else if(!Files.isDirectory(file) && (findType == FIND_TYPES.FILES || findType == FIND_TYPES.BOTH)) {				
				matchedFiles.add(file);				
			}
		}
	}

	// Prints the total number of
	// matches to standard out.
	public ArrayList<Path> done() {
		return matchedFiles;
	}

	// Invoke the pattern matching
	// method on each file.
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
		find(file);
		return CONTINUE;
	}

	// Invoke the pattern matching
	// method on each directory.
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
		find(dir);
		return CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) {
		System.err.println(exc);
		return CONTINUE;
	}
}
