package com.example.demo.task;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Task")
public class Task {
	    @Id
	    @Column(name="id")
	    private int id;
	    @Column(name="title")
	    private String title;
	    @Column(name="description")
	    private String description;
	    @Column(name="due_date")
	    private java.sql.Date dueDate;
	    @Column(name="priority")
	    private String priority;
	    @Column(name="status")
	    private String status;



		public Task(int id, String title, String description, Date dueDate, String priority, String status) {
			super();
			this.id = id;
			this.title = title;
			this.description = description;
			this.dueDate = dueDate;
			this.priority = priority;
			this.status = status;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public java.sql.Date getDueDate() {
			return dueDate;
		}

		public void setDueDate(java.sql.Date dueDate) {
			this.dueDate = dueDate;
		}

		public String getPriority() {
			return priority;
		}

		public void setPriority(String priority) {
			this.priority = priority;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		@Override
		public String toString() {
			return "Task [id=" + id + ", title=" + title + ", description=" + description + ", dueDate=" + dueDate
					+ ", priority=" + priority + ", status=" + status + "]";
		}

		public Task() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
		
}