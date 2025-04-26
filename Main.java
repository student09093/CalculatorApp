package com.example.calc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    String title, status;
    ErrorPriority priority;
    Date dateOccurred;
    Executor executor;
    
    public void setTitle(String title) {
        try {
            if (title == null || title.isEmpty()) {
                throw new IllegalArgumentException("Заголовок не может быть пустым");
            }
            this.title = title;
        } catch (Exception e) {
            System.err.println("Ошибка при установке заголовка: " + e.getMessage());
        }
    }

    public void setStatus(String status) {
        try {
            if (status == null || status.isEmpty()) {
                throw new IllegalArgumentException("Статус не может быть пустым");
            }
            this.status = status;
        } catch (Exception e) {
            System.err.println("Ошибка при установке статуса: " + e.getMessage());
        }
    }

    public void setPriority(ErrorPriority priority) {
        try {
            if (priority == null) {
                throw new IllegalArgumentException("Приоритет не может быть пустым");
            }
            this.priority = priority;
        } catch (Exception e) {
            System.err.println("Ошибка при установке приоритета: " + e.getMessage());
        }
    }
    private double num1 = 0, num2 = 0;
private String operator = "";

private void clearDisplay() {
    display.setText("");
    num1 = 0;
    num2 = 0;
    operator = "";
}

private void calculateResult() {
    num2 = Double.parseDouble(display.getText().toString());

    double result = 0;

    switch (operator) {
        case "+":
            result = num1 + num2;
            break;
        case "-":
            result = num1 - num2;
            break;
        case "*":
            result = num1 * num2;
            break;
        case "/":
            if (num2 != 0) {
                result = num1 / num2;
            } else {
                display.setText("Ошибка");
                return;
            }
            break;
    }

    display.setText(String.valueOf(result));
    operator = "";
}


    public void setDateOccurred(Date dateOccurred) {
        try {
            if (dateOccurred == null) {
                throw new IllegalArgumentException("Дата не может быть пустой");
            }
            this.dateOccurred = dateOccurred;
        } catch (Exception e) {
            System.err.println("Ошибка при установке даты возникновения: " + e.getMessage());
        }
    }

    public void setExecutor(Executor executor) {
        try {
            if (executor == null) {
                throw new IllegalArgumentException("Исполнитель не может быть пустым");
            }
            this.executor = executor;
        } catch (Exception e) {
            System.err.println("Ошибка при установке исполнителя: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        List<Error> errorList = new ArrayList<>();
        ErrorTracker errorTracker = new ErrorTracker(errorList);

        String description = "Ошибка при загрузке страницы";
        Date dateOccurred = new Date();
        String status = "Ожидает решения";
        ErrorPriority priority = ErrorPriority.HIGH;

        Executor executor = new Executor(1, "Иван Иванов");

        List<ErrorComment> comments = new ArrayList<>();
        comments.add(new ErrorComment("Ошибка воспроизводится при попытке загрузить страницу."));

        try {
            errorTracker.createError(description, dateOccurred, status, priority, executor, comments);
        } catch (Exception e) {
            System.err.println("Ошибка при создании ошибки: " + e.getMessage());
        }

        try {
            Error error = errorTracker.getErrorById(1);
            if (error != null) {
                System.out.println("Ошибка найдено: " + error.getDescription());
            } else {
                System.out.println("Ошибка не найдена.");
            }
        } catch (Exception e) {
            System.err.println("Ошибка при получении ошибки: " + e.getMessage());
        }

        try {
            errorTracker.updateErrorStatus(1, "В процессе решения");
        } catch (Exception e) {
            System.err.println("Ошибка при обновлении статуса: " + e.getMessage());
        }

        try {
            errorTracker.createError("", dateOccurred, status, priority, executor, comments);
        } catch (Exception e) {
            System.err.println("Ошибка при создании ошибки: " + e.getMessage());
        }

        try {
            errorTracker.getErrorById(99);
        } catch (Exception e) {
            System.err.println("Ошибка при получении ошибки по ID: " + e.getMessage());
        }
    }
}
