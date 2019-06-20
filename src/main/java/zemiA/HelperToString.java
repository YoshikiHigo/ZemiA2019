package zemiA;

import java.util.ArrayList;
import java.util.HashSet;




/**
 * @author gruidae
 *
 * toString メソッド用のヘルパークラス.
 */
public class HelperToString {

	/** AccessModifier -> String */
	public static String toString(AccessModifier accessModifier)
	{
		String str = null;
		switch(accessModifier) {
		case PRIVATE:    str = "private";  break;
		case PROTECTED:  str = "protected"; break;
		case PUBLIC:     str = "public"; break;
		default:          str = "package protected";
		}

		return str;
	}


	/** ClassAbstraction -> String */
	public static String toString(ClassAbstraction classAbstraction)
	{
		String str = null;
		switch(classAbstraction) {
		case ABSTRACT_CLASS:   str = "abstract";  break;
		case INTERFACE_CLASS:  str = "interface"; break;
		default:               str = "instance";
		}

		return str;
	}


	/** boolean -> String */
	public static String toString(boolean isSomething, String argumentString)
	{
		String str = null;
		switch(argumentString) {
		case "static":    str = isSomething?"static":"dynamic";  break;
		case "abstract":  str = isSomething?", abstruct":"";  break;
		default:          str = "";
		}
		return str;
	}


	/** ArrayList<String> -> String */
	public static String toString(ArrayList<String> argumentsList)
	{
		String str = null;
		boolean isFirst = true;
		for (String argument: argumentsList) {
			if (!isFirst)  str = str + ", ";
			str = str + argument;
			isFirst = false;
		}

		return str;
	}


	/** Disharmony -> String */
	public static String toString(HashSet<Disharmony> disharmnonySet)
	{
		String str = null;
		boolean isFirst = true;
		for (Disharmony disharmony: disharmnonySet) {
			if (!isFirst)  str = str + "  ";
			switch(disharmony) {
			case GOD_CLASS:
				str = str + "\"God Class\"";  break;
			case FEATURE_ENVY:
				str = str + "\"Feature Envy\"";  break;
			case DATA_CLASS:
				str = str + "\"Data Class\"";  break;
			case BRAIN_METHOD:
				str = str + "\"Brain Method\"";  break;
			case BRAIN_CLASS:
				str = str + "\"Brain Class\"";  break;
			case SIGNIFICANT_DUPLICATION:
				str = str + "\"Significant_Duplication\"";  break;
			case INTENSIVE_COUPLING:
				str = str + "\"Intensive Coupling\"";  break;
			case DISPERSED_COUPLING:
				str = str + "\"Dispersed Coupling\"";  break;
			case SHOTGUN_SURGERY:
				str = str + "\"Shotgun Sergery\"";  break;
			case REFUSED_PARENT_BEQUEST:
				str = str + "\"Refused Parent Bequest\"";  break;
			case TRADITION_BREAKER:
				str = str + "\"Tradition Breaker\"";  break;
			default:
				str = str + "";  break;
			}
			isFirst = false;
		}
		return str;
	}

}
