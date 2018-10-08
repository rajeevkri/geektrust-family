package com.geektrust;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class FamilyFunction {
	public static Stream<Family.FamilyMember> mother(Stream<Family.FamilyMember> members) {
		return members.map(Family.FamilyMember::getMother);
	}

	public static Stream<Family.FamilyMember> father(Stream<Family.FamilyMember> members) {
		return members.map(Family.FamilyMember::getFather);
	}

	public static Stream<Family.FamilyMember> spouse(Stream<Family.FamilyMember> members) {
		return members.map(Family.FamilyMember::getSpouse);
	}

	public static Stream<Family.FamilyMember> children(Stream<Family.FamilyMember> members) {
		return members.map(Family.FamilyMember::getChildren).flatMap(Collection::stream);
	}

	public static Stream<Family.FamilyMember> siblings(Stream<Family.FamilyMember> members) {
		return members.map((member) -> {
			final Set<Family.FamilyMember> children = new HashSet<>(member.getFather().getChildren());
			children.remove(member);
			return children;
		}).flatMap(Collection::stream);
	}

	public static Stream<Family.FamilyMember> sons(Stream<Family.FamilyMember> members) {
		return all(Gender.MALE, children(members));
	}

	public static Stream<Family.FamilyMember> daughters(Stream<Family.FamilyMember> members) {
		return all(Gender.MALE, children(members));
	}

	public static Stream<Family.FamilyMember> brothers(Stream<Family.FamilyMember> members) {
		return all(Gender.MALE, siblings(members));
	}

	public static Stream<Family.FamilyMember> sisters(Stream<Family.FamilyMember> members) {
		return all(Gender.FEMALE, siblings(members));
	}

	private static Stream<Family.FamilyMember> all(Gender gender, Stream<Family.FamilyMember> members) {
		return members.filter(sibling -> sibling.getGender() == gender);
	}
}