package com.knuchel.start.android.model;

public class SamplePrefs {
	private boolean CheckboxPreference;
	private String ListPreference;
	private String editTextPreference;
	private String ringtonePreference;
	private String secondEditTextPreference;
	private String customPref;

	public void setCheckboxPreference(boolean checkboxPreference) {
		CheckboxPreference = checkboxPreference;
	}

	public boolean getCheckboxPreference() {
		return CheckboxPreference;
	}

	public boolean isCheckboxPreference() {
		return CheckboxPreference;
	}

	public void setListPreference(String listPreference) {
		ListPreference = listPreference;
	}

	public String getListPreference() {
		return ListPreference;
	}

	public void setEditTextPreference(String editTextPreference) {
		this.editTextPreference = editTextPreference;
	}

	public String getEditTextPreference() {
		return editTextPreference;
	}

	public void setRingtonePreference(String ringtonePreference) {
		this.ringtonePreference = ringtonePreference;
	}

	public String getRingtonePreference() {
		return ringtonePreference;
	}

	public void setSecondEditTextPreference(String secondEditTextPreference) {
		this.secondEditTextPreference = secondEditTextPreference;
	}

	public String getSecondEditTextPreference() {
		return secondEditTextPreference;
	}

	public void setCustomPref(String customPref) {
		this.customPref = customPref;
	}

	public String getCustomPref() {
		return customPref;
	}

}
