package com.example.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class RestTask {
	private Long id;

	private String name;

	private String description;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date creationDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date deadLine;

	public RestTask id(Long id) {
		this.id = id;
		return this;
	}

	/**
	 * id задачи
	 * @return id
	 */

	@JsonProperty("id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RestTask name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * название задачи
	 * @return name
	 */

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RestTask description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * описание задачи
	 * @return description
	 */

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RestTask creationDate(Date creationDate) {
		this.creationDate = creationDate;
		return this;
	}

	/**
	 * дата создания
	 * @return creationDate
	 */
	@JsonProperty("creationDate")
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public RestTask deadLine(Date deadLine) {
		this.deadLine = deadLine;
		return this;
	}

	/**
	 * дата завершения
	 * @return deadLine
	 */
	@JsonProperty("deadLine")
	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		RestTask restTask = (RestTask) o;
		return Objects.equals(this.id, restTask.id) &&
				Objects.equals(this.name, restTask.name) &&
				Objects.equals(this.description, restTask.description) &&
				Objects.equals(this.creationDate, restTask.creationDate) &&
				Objects.equals(this.deadLine, restTask.deadLine);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, description, creationDate, deadLine);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class RestTask {\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
		sb.append("    deadLine: ").append(toIndentedString(deadLine)).append("\n");
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
