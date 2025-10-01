# Exercises (Ex1 & Ex2)

This repository contains Java console-based implementations demonstrating design patterns.

 - Ex1: Creational, Structural, Behavioral design patterns
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
- **Use case**: Create families of related UI components (e.g., buttons, text fields) across platforms without changing client code.
- **Key idea**: `UIFactory` defines creation methods and is realized by `WindowsUIFactory`/`MacUIFactory` to produce matching `Button` and `TextField` pairs; client code selects a factory once and builds a consistent UI family.

#### Singleton
- **Use case**: Share a single instance for global concerns (e.g., configuration, logging, schedule manager).
- **Key idea**: A private constructor with a static `getInstance()` ensures a unique instance (e.g., `creational/singleton/Logger`, and in Ex2 `ScheduleManager`/`Logger`), providing centralized access without passing instances around.

### Structural
#### Adapter
- **Use case**: Integrate an INR-based payment service with a USD-only processor expected by the client.
- **Key idea**: `InrToUsdAdapter` implements the client's `PaymentProcessor` interface and internally uses `ThirdPartyUSPay`, converting INR→USD and mapping method signatures so the client remains unchanged.

#### Decorator
- **Use case**: Add SMS/Push notifications on top of a base Email notifier at runtime.
- **Key idea**: `EmailNotifier` is the base; `SmsDecorator`/`PushDecorator` wrap a `Notifier` and delegate while appending behavior, allowing layered notifications without modifying the base class.

### Behavioral
#### Chain of Responsibility
- **Use case**: Expense approval workflow.
- **Key idea**: `Manager` (≤ 500) handles small expenses, `Director` (≤ 5,000) handles medium, and anything higher is approved by `CEO`. The chain is wired using `Approver.setNext(...)`, and each approver either approves or forwards via `passToNext(...)`.

#### Observer
- **Use case**: Weather station notifying multiple displays.
- **Key idea**: `WeatherData` stores `Observer` listeners and calls `update(temp, humidity, pressure)` on each when `setMeasurements(...)` is invoked; displays implement `Observer` to react instantly.

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
cd Ex2/src
javac $(find . -name "*.java")
java ex2.app.Main
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
Add: Morning Exercise, 7:00–7:30, High → Task added successfully. No conflicts.
Complete: Morning Exercise → Task marked as completed.
Complete again: Morning Exercise → Error: Task is already completed.
Add: Meeting, 8:00–8:30, Low → Task added successfully. No conflicts.
View by priority (6):
07:00 - 07:30: Morning Exercise [High] (Completed)
08:00 - 08:30: Meeting [Low]
```

## Requirements 
- Java 8+.

