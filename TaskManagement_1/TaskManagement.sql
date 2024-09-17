create database Task;

use Task;

CREATE TABLE task (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    due_date DATE,
    priority ENUM('LOW', 'MEDIUM', 'HIGH'),
    status ENUM('PENDING', 'IN_PROGRESS', 'COMPLETED')
);

INSERT INTO task (title, description, due_date, priority, status) 
VALUES 
('Complete API Design', 'Design the REST API for task management', '2024-09-25', 'HIGH', 'PENDING'),
('Implement JWT Authentication', 'Secure the API with JWT tokens', '2024-09-30', 'MEDIUM', 'IN_PROGRESS'),
('Database Integration', 'Integrate the API with MySQL database', '2024-09-28', 'HIGH', 'PENDING'),
('Write Unit Tests', 'Create unit tests for all the API endpoints', '2024-10-02', 'MEDIUM', 'PENDING'),
('Create Documentation', 'Document the API with Swagger or Postman', '2024-10-05', 'LOW', 'PENDING'),
('Fix Bugs in Authentication', 'Resolve any issues related to user authentication', '2024-09-27', 'HIGH', 'IN_PROGRESS'),
('Deploy API', 'Deploy the API to a cloud service or server', '2024-10-10', 'HIGH', 'PENDING'),
('Test API with Postman', 'Ensure all endpoints are functioning as expected', '2024-09-26', 'MEDIUM', 'COMPLETED'),
('Optimize Performance', 'Improve the performance of API endpoints', '2024-10-08', 'MEDIUM', 'PENDING'),
('Review Code', 'Review the code to ensure it follows best practices', '2024-09-29', 'LOW', 'PENDING');

select * from task;

