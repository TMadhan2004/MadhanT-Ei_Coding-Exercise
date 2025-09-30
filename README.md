# Exercises (Ex1 & Ex2)

This repository contains Java console-based implementations demonstrating design patterns.

 - Ex1: Behavioral design patterns
 - Ex2: Astronaut Daily Schedule Organizer Programming Exercise 

---
## Table of Contents

- [Ex1: Design Patterns (Creational, Structural, Behavioral)](#ex1-design-patterns-creational-structural-behavioral)
  - [Creational](#creational)
    - [Abstract Factory](#abstract-factory)
    - [Singleton](#singleton)
  - [Structural](#structural)
    - [Adapter](#adapter)
    - [Decorator](#decorator)
  - [Behavioral](#behavioral)
    - [Chain of Responsibility](#chain-of-responsibility)
    - [Observer](#observer)
- [Ex2: Astronaut Daily Schedule Organizer (Console App)](#ex2-astronaut-daily-schedule-organizer-programming-exercise)
  - [Overview](#overview)
  - [Features](#features)
  - [Design](#design)
  - [Key Classes](#key-classes)
  - [Input Validation & Errors](#input-validation--errors)
  - [Example Session](#example-session)
  - [Performance Considerations](#performance-considerations)
  - [Extensibility Ideas](#extensibility-ideas)
- [Project Structure](#project-structure)
- [Requirements & Notes](#requirements--notes)

## Ex1: Design Patterns (Creational, Structural, Behavioral)

Location: `Ex1/`

### Creational
#### Abstract Factory
- Location: `Ex1/creational/abstract-factory/`
- Produces related UI components (e.g., `WindowsButton`, `WindowsTextField`) via `UIFactory` without coupling to concrete classes.

#### Singleton
- Location: used broadly in this repo
- Examples: `Ex2/ScheduleManager.java` and `Ex2/Logger.java` (single instance lifecycle)

### Structural
#### Adapter
- Location: `Ex1/structural/adapter/`
- Example: `InrToUsdAdapter` adapts INR amounts to USD for a client expecting USD (interface compatibility).

#### Decorator
- Location: `Ex1/structural/decorator/`
- Example: `EmailNotifier` with `SmsDecorator` / `PushDecorator` layering additional notifications (behavior extension at runtime).

### Behavioral
#### Chain of Responsibility
- **Use case**: Expense approval workflow.
- **Flow**: Manager (≤ 500) → Director (≤ 5,000) → CEO (> 5,000)
- **Key idea**: Each handler processes or passes the request to the next.

#### Observer
- **Use case**: Weather station notifying multiple displays.
- **Key idea**: Subject (`WeatherData`) maintains a list of `Observer`s and notifies them on data changes.

---

## Ex2: Astronaut Daily Schedule Organizer Programming Exercise

Location: `Ex2/`

### Overview
A console-based application that helps astronauts organize their daily schedules with non-overlapping times, task priorities, and completion status, designed using the Singleton, Factory, and Observer patterns while following SOLID principles

### Features
- **Add task** with description, start/end time (`H:mm` or `HH:mm`), and priority (High/Medium/Low).
- **Validate overlaps**: New/edited tasks cannot overlap existing ones.
- **View tasks**:
  - Sorted by start time.
  - Sorted by priority order (High → Medium → Low) via menu option 6.
- **Edit task** with conflict validation; preserves completion state on request.
- **Remove task**.
- **Mark task as completed** with safeguards:
  - If a task is already completed → error.
  - If all tasks are completed → shows "No active tasks available."
- **Filter by priority (implicit)**: Option 6 shows all tasks ordered by priority; can be extended to filter a single priority.
- **Graceful empty-state handling** for Edit/Remove/Complete.
- **Logging** of key events (add/remove/edit/complete) with timestamps.

### Design
- **Patterns**
  - Singleton: `ScheduleManager` (single source of truth for tasks); `Logger`.
  - Factory: `TaskFactory` (parses/validates input; constructs `Task`).
  - Observer: `ScheduleObserver` (conflict/update notifications), `ConflictObserver`, `LoggingObserver`.
- **SOLID**
  - SRP: UI (`Main`, `ConsoleController`), domain (`Task`, `Priority`), manager (`ScheduleManager`), factory (`TaskFactory`), logging (`Logger`), observers separated.
  - OCP: Add new observers or views without changing core manager.
  - LSP/ISP/DIP: Observers via interface; UI depends on abstractions.

### Key Classes
- `Main.java`: Menu + user input (Scanner). Wires manager, factory, observers.
- `ConsoleController.java`: Implements actions for menu options.
- `ScheduleManager.java`: Core business logic; CRUD, conflict checks, sorted retrieval, observer notifications.
- `TaskFactory.java`: Parses times, validates priority, aggregates errors.
- `Task.java`: Entity model (description, `LocalTime` start/end, priority, completed, `overlaps()`), `Comparable` by start.
- `Priority.java`: Enum (HIGH, MEDIUM, LOW) with `fromString()` mapping.
- `ScheduleObserver.java`: Observer interface (onAdd/onRemove/onComplete/onEdit/onConflict).
- `ConflictObserver.java`: Prints conflicts.
- `LoggingObserver.java`: Writes timestamped logs through `Logger`.
- `Logger.java`: Minimal singleton logger.

### Input Validation & Errors
- Times accept `7:00` or `07:00` formats.
- Aggregated errors from `TaskFactory` (examples):
  - `Error: Invalid time format. Invalid priority.`
  - `Error: Description is required`
- Operational errors:
  - `Error: Task conflicts with existing task "<desc>".`
  - `Error: Task not found.`
  - `Error: Task is already completed.`
- Empty-state messages for Edit/Remove/Complete:
  - `No tasks scheduled for the day.`
  - `No active tasks available. All tasks are completed.`

### Usage
Build & run:
```bash
cd Ex2
javac *.java
java Main
```

Menu:
```
1) Add Task
2) Remove Task
3) View Tasks (sorted)
4) Edit Task
5) Mark Task Completed
6) View Tasks by Priority
0) Exit
```

### Example Session
```
Add: Brush, 7:00–7:30, Medium → Task added successfully. No conflicts.
Complete: Brush → Task marked as completed.
Complete again: Brush → Error: Task is already completed.
Add: Bathing, 8:00–8:30, Low → Task added successfully. No conflicts.
View by priority (6):
07:00 - 07:30: Brush [High] (Completed)
08:00 - 08:30: Bathing [Low]
```

## Requirements 
- Java 8+.

