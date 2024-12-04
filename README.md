# Advent of Code 2024

[![Build and Test](https://github.com/edwinhern/advent-of-code-2024/actions/workflows/build.yml/badge.svg)](https://github.com/edwinhern/advent-of-code-2024/actions/workflows/build.yml)

My solutions for [Advent of Code 2024](https://adventofcode.com/2024) implemented in Java.

## Project Structure

- `src/main/java/dayXX` - Contains solution for each day
- `src/main/resources/dayXX` - Contains input files for each day's puzzle

## Running Solutions

This is a Maven project. To run:

1. Clone the repository
2. Build the project: `mvn clean package`
3. Run individual day solutions through their respective main methods

## Development

- Java 17
- Maven for build management
- Spotless for code formatting

### Code Formatting

Format code before committing:

```bash
mvn spotless:apply
```

## Progress

- [x] Day 1
- [x] Day 2
- [ ] Day 3-25 (Coming Soon)
