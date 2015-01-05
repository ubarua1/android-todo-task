package com.todotask.db.model;

/**
 * Individual Task item POJO
 */
public class TaskItem {

	private String mTitle;

	private String mDescription;

	private int mPriority;

	private boolean isDone;

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public String getDescription() {
		return mDescription;
	}

	public void setDescription(String mDescription) {
		this.mDescription = mDescription;
	}

	public int getPriority() {
		return mPriority;
	}

	public void setPriority(int mPriority) {
		this.mPriority = mPriority;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	@Override
	public int hashCode() {

		return 37 + mTitle.hashCode() * mDescription.hashCode() *
				mPriority;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final TaskItem other = (TaskItem) obj;
		if(!mTitle.equals(other.getTitle())) return false;
		if(!mDescription.equals(other.mDescription)) return false;
		if(!(mPriority == other.mPriority)) return false;

		return true;
	}
}
