package com.geektrust;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

import com.geektrust.Family.FamilyMember;

import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.of;

public enum Relation {
    MOTHER(member -> FamilyFunction.mother(of(member))),
    FATHER(member -> FamilyFunction.father(of(member))),
    SPOUSE(member -> FamilyFunction.spouse(of(member))),
    BROTHER(member -> FamilyFunction.brothers(of(member))),
    SISTER(member -> FamilyFunction.sisters(of(member))),
    SON(member -> FamilyFunction.sons(of(member))),
    DAUGHTER(member -> FamilyFunction.daughters(of(member))),
    CHILDREN(member -> FamilyFunction.children(of(member))),

    GRANDSON(member -> SON.to(FamilyFunction.children(of(member)))),
    GRANDDAUGHTER(member -> DAUGHTER.to(FamilyFunction.children(of(member)))),
    COUSIN(member -> union(
            CHILDREN.to(FamilyFunction.siblings(FamilyFunction.father(of(member)))),
            CHILDREN.to(FamilyFunction.siblings(FamilyFunction.mother(of(member)))))),
    BROTHER_IN_LAW(member -> union(
            BROTHER.to(FamilyFunction.spouse(of(member))),
            SPOUSE.to(FamilyFunction.sisters(of(member))))),
    SISTER_IN_LAW(member -> union(
            SISTER.to(FamilyFunction.spouse(of(member))),
            SPOUSE.to(FamilyFunction.brothers(of(member))))),
    PATERNAL_UNCLE(member -> union(
            BROTHER.to(FamilyFunction.father(of(member))),
            BROTHER_IN_LAW.to(FamilyFunction.father(of(member))))),
    MATERNAL_UNCLE(member -> union(
            BROTHER.to(FamilyFunction.mother(of(member))),
            BROTHER_IN_LAW.to(FamilyFunction.mother(of(member))))),
    PATERNAL_AUNT(member -> union(
            SISTER.to(FamilyFunction.father(of(member))),
            SISTER_IN_LAW.to(FamilyFunction.father(of(member))))),
    MATERNAL_AUNT(member -> union(
            SISTER.to(FamilyFunction.mother(of(member))),
            SISTER_IN_LAW.to(FamilyFunction.mother(of(member)))));

    private Function<Family.FamilyMember, Stream<Family.FamilyMember>> function;

    Relation(Function<Family.FamilyMember, Stream<Family.FamilyMember>> relationFn) {
        this.function = relationFn;
    }

    public Set<Family.FamilyMember> to(Family.FamilyMember member) {
    	Stream<FamilyMember> apply = this.function.apply(member);
    	System.out.println();
        return apply
                .collect(toSet());
    }

    private Stream<Family.FamilyMember> to(Stream<Family.FamilyMember> members) {
        return members
                .map(this::to)
                .flatMap(Collection::stream);
    }

    private static Stream<Family.FamilyMember> union(Stream<Family.FamilyMember> ...members) {
        return Arrays.asList(members)
                .stream()
                .reduce(Stream::concat)
                .orElseThrow(RuntimeException::new);

    }
}