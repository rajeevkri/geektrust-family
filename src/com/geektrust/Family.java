package com.geektrust;

import static java.util.stream.Stream.of;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

public class Family {
	public static final String NULL = "N_U_L_L";

	private Map<String, FamilyMember> members = new HashMap<>();

	public Family(Consumer<Family> populateFn) {
		this.addMember(NULL, null);
		populateFn.accept(this);
	}

	public Family() {

	}

	public FamilyMember addMember(String name, Gender gender) {
		return Optional.ofNullable(members.get(name)).orElseGet(() -> {
			final FamilyMember newMember = new FamilyMember(name, gender);
			members.put(name, newMember);
			return newMember;
		});
	}

	public FamilyMember get(String name) {
		if (members.get(name) != null)
			return members.get(name);
		throw new MemberNotFoundInFamilyException("No Member available");
	}

	public Set<String> getAllFamilyMemberNames() {
		return members.keySet();
	}

	public FamilyMember oldGenerationPerson(FamilyMember member1, FamilyMember member2) {
		return member1.level > member2.level ? member2 : member1;
	}

	public Relation getRelationShip(FamilyMember member1, FamilyMember member2) {
		int levelDiff = member1.level - member2.level;
		Set<FamilyMember> set = Relation.BROTHER.to(member2);
//		Relation.BROTHER.to(FamilyFunction.spouse(of(member2)));

		if (Relation.PATERNAL_UNCLE.to(member2).contains(member1)) {
			return Relation.PATERNAL_UNCLE;
		}
		return Relation.COUSIN;
	}

	public class FamilyMember {
		private String name;
		private Gender gender;
		private FamilyMember spouse;
		private FamilyMember mother;
		private FamilyMember father;
		private Set<FamilyMember> children = new HashSet<>();
		private int level = 1;

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		private FamilyMember(String name, Gender gender) {
			this.name = name;
			this.gender = gender;
			this.spouse = members.get(NULL);
			this.mother = members.get(NULL);
			this.father = members.get(NULL);
		}

		public String getName() {
			return name;
		}

		public Gender getGender() {
			return gender;
		}

		public FamilyMember getMother() {
			return mother;
		}

		public FamilyMember getFather() {
			return father;
		}

		public Set<FamilyMember> getChildren() {
			return children;
		}

		public FamilyMember getSpouse() {
			return spouse;
		}

		public void isSpouseOf(FamilyMember member) {
			this.spouse = member;
			member.spouse = this;
			this.level = member.level;
		}

		public void isParentOf(FamilyMember member) {
			this.children.add(member);
			this.spouse.children.add(member);
			if (this.gender == Gender.MALE) {
				member.father = this;
			} else {
				member.mother = this;
			}
			member.level = level + 1;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (!(o instanceof FamilyMember)) {
				return false;
			}

			FamilyMember that = (FamilyMember) o;

			return !(name != null ? !name.equals(that.name) : that.name != null);
		}

		@Override
		public int hashCode() {
			return name != null ? name.hashCode() : 0;
		}

		@Override
		public String toString() {
			return this.getName();
		}
	}
}
