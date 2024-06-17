package com.example.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RestUser {
	private Long id;

	private String name;

	private String surname;

	private String mail;

	private List<RestTask> tasks;

	public RestUser id(Long id) {
		this.id = id;
		return this;
	}

	/**
	 * id пользователя
	 * @return id
	 */

	@JsonProperty("id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RestUser name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * имя пользователя
	 * @return name
	 */

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RestUser surname(String surname) {
		this.surname = surname;
		return this;
	}

	/**
	 * фамилия пользователя
	 * @return surname
	 */

	@JsonProperty("surname")
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public RestUser mail(String mail) {
		this.mail = mail;
		return this;
	}

	/**
	 * email пользователя
	 * @return mail
	 */

	@JsonProperty("mail")
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public RestUser tasks(List<RestTask> tasks) {
		this.tasks = tasks;
		return this;
	}

	public RestUser addTasksItem(RestTask tasksItem) {
		if (this.tasks == null) {
			this.tasks = new ArrayList<>();
		}
		this.tasks.add(tasksItem);
		return this;
	}

	/**
	 * Get tasks
	 * @return tasks
	 */
	@JsonProperty("tasks")
	public List<RestTask> getTasks() {
		return tasks;
	}

	public void setTasks(List<RestTask> tasks) {
		this.tasks = tasks;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		RestUser restUser = (RestUser) o;
		return Objects.equals(this.id, restUser.id) &&
				Objects.equals(this.name, restUser.name) &&
				Objects.equals(this.surname, restUser.surname) &&
				Objects.equals(this.mail, restUser.mail) &&
				Objects.equals(this.tasks, restUser.tasks);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, surname, mail, tasks);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class RestUser {\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    surname: ").append(toIndentedString(surname)).append("\n");
		sb.append("    mail: ").append(toIndentedString(mail)).append("\n");
		sb.append("    tasks: ").append(toIndentedString(tasks)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

}
