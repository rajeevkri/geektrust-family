package com.geektrust;

import org.junit.Assert;
import org.junit.Test;

public class FamilyTest {

	@Test
	public void testBlankFamily() {
		final Family blankFamily = new Family();
		Assert.assertEquals(blankFamily.getAllFamilyMemberNames().size(), 0);
	}

	@Test
	public void testSpouseRelation() {
		final Family family = new Family();
		Family.FamilyMember kingShan = family.addMember("King Shan", Gender.MALE);
		Family.FamilyMember queenAnga = family.addMember("Queen Anga", Gender.FEMALE);
		Assert.assertEquals(family.getAllFamilyMemberNames().size(), 2);
		kingShan.isSpouseOf(queenAnga);
		Assert.assertEquals(kingShan.getSpouse().getName(), "Queen Anga");
	}

	@Test(expected = MemberNotFoundInFamilyException.class)
	public void testMemberNotFound() {
		final Family family = new Family();
		family.addMember("satvi", Gender.FEMALE);
		Assert.assertEquals(family.getAllFamilyMemberNames().size(), 1);
		family.get("saayan");
	}

	@Test
	public void testBrotherRelation() {
		final Family family = new Family();
		Family.FamilyMember chit = family.addMember("chit", Gender.MALE);
		Family.FamilyMember drita = family.addMember("drita", Gender.MALE);
		Family.FamilyMember vrita = family.addMember("vrita", Gender.MALE);
		Family.FamilyMember ambi = family.addMember("ambi", Gender.FEMALE);
		chit.isSpouseOf(ambi);
		chit.isParentOf(drita);
		chit.isParentOf(vrita);
		Assert.assertEquals(family.getAllFamilyMemberNames().size(), 4);
		Assert.assertTrue(Relation.BROTHER.to(drita).contains(vrita));
	}

	@Test
	public void testCompleteFamilyAndRelation() {
		final Family shanFamily = new Family(FamilyTest::populate);
		final Family.FamilyMember kingShan = shanFamily.get("King Shan");
		final Family.FamilyMember chit = shanFamily.get("chit");

		final Family.FamilyMember saayan = shanFamily.get("saayan");
		final Family.FamilyMember drita = shanFamily.get("drita");
		final Family.FamilyMember asva = shanFamily.get("asva");
		final Family.FamilyMember krpi = shanFamily.get("krpi");
		final Family.FamilyMember savya = shanFamily.get("savya");
		final Family.FamilyMember chika = shanFamily.get("chika");
		final Family.FamilyMember satvi = shanFamily.get("satvi");
		final Family.FamilyMember vila = shanFamily.get("vila");
		final Family.FamilyMember vyan = shanFamily.get("vyan");
		final Family.FamilyMember ish = shanFamily.get("ish");
		final Family.FamilyMember vich = shanFamily.get("vich");

		Assert.assertTrue(Relation.SISTER_IN_LAW.to(saayan).contains(krpi));
		Assert.assertTrue(Relation.COUSIN.to(drita).contains(saayan));
		Assert.assertTrue(Relation.COUSIN.to(drita).contains(savya));
		Assert.assertTrue(Relation.COUSIN.to(drita).contains(chika));
		Assert.assertTrue(Relation.COUSIN.to(drita).contains(satvi));
		Assert.assertTrue(Relation.COUSIN.to(drita).contains(vila));
		Assert.assertFalse(Relation.COUSIN.to(drita).contains(vich));
		Assert.assertTrue(Relation.SISTER_IN_LAW.to(saayan).contains(krpi));
		Assert.assertFalse(Relation.GRANDDAUGHTER.to(asva).contains(vich));
		Assert.assertEquals(Relation.GRANDDAUGHTER.to(asva).size(), 0);

		Assert.assertTrue(Relation.PATERNAL_UNCLE.to(drita).contains(vich));
		Assert.assertTrue(Relation.PATERNAL_UNCLE.to(drita).contains(ish));
		Assert.assertTrue(Relation.PATERNAL_UNCLE.to(drita).contains(vyan));
		Assert.assertFalse(Relation.PATERNAL_UNCLE.to(drita).contains(asva));

		Family.FamilyMember misa = shanFamily.get("misa");
		Assert.assertTrue(saayan.getChildren().contains(misa));
		Assert.assertEquals(Relation.GRANDDAUGHTER.to(asva).size(), 0);
		Assert.assertEquals(saayan.getFather().getName(), "vyan");
		Assert.assertEquals(saayan.getGender(), Gender.MALE);
		Assert.assertEquals(saayan.getMother().getName(), "satya");
		Assert.assertEquals(saayan.getSpouse().getName(), "mina");
//		Assert.assertEquals(ish.getLevel(), drita.getLevel() +2);
		System.out.println(kingShan.getLevel() + " -- " +  chit.getLevel() );
	}

	private static void populate(Family family) {
		Family.FamilyMember kingShan = family.addMember("King Shan", Gender.MALE);
		Family.FamilyMember queenAnga = family.addMember("Queen Anga", Gender.FEMALE);
		Family.FamilyMember ish = family.addMember("ish", Gender.MALE);
		Family.FamilyMember chit = family.addMember("chit", Gender.MALE);
		Family.FamilyMember ambi = family.addMember("ambi", Gender.FEMALE);
		Family.FamilyMember vich = family.addMember("vich", Gender.MALE);
		Family.FamilyMember lika = family.addMember("lika", Gender.FEMALE);
		Family.FamilyMember satya = family.addMember("satya", Gender.FEMALE);
		Family.FamilyMember vyan = family.addMember("vyan", Gender.MALE);
		Family.FamilyMember drita = family.addMember("drita", Gender.MALE);
		Family.FamilyMember jaya = family.addMember("jaya", Gender.FEMALE);
		Family.FamilyMember vrita = family.addMember("vrita", Gender.MALE);
		Family.FamilyMember vila = family.addMember("vila", Gender.MALE);
		Family.FamilyMember jinki = family.addMember("jinki", Gender.FEMALE);
		Family.FamilyMember chika = family.addMember("chika", Gender.FEMALE);
		Family.FamilyMember kpila = family.addMember("kpila", Gender.MALE);
		Family.FamilyMember satvi = family.addMember("satvi", Gender.FEMALE);
		Family.FamilyMember asva = family.addMember("asva", Gender.MALE);
		Family.FamilyMember savya = family.addMember("savya", Gender.MALE);
		Family.FamilyMember krpi = family.addMember("krpi", Gender.FEMALE);
		Family.FamilyMember saayan = family.addMember("saayan", Gender.MALE);
		Family.FamilyMember mina = family.addMember("mina", Gender.FEMALE);
		Family.FamilyMember jata = family.addMember("jata", Gender.MALE);
		Family.FamilyMember driya = family.addMember("driya", Gender.FEMALE);
		Family.FamilyMember mnu = family.addMember("mnu", Gender.MALE);
		Family.FamilyMember lavnya = family.addMember("lavnya", Gender.FEMALE);
		Family.FamilyMember gru = family.addMember("gru", Gender.MALE);
		Family.FamilyMember kriya = family.addMember("kriya", Gender.MALE);
		Family.FamilyMember misa = family.addMember("misa", Gender.MALE);

		// Assigning Relations
		kingShan.isSpouseOf(queenAnga);
		kingShan.isParentOf(ish);
		queenAnga.isParentOf(ish);
		kingShan.isParentOf(chit);
		queenAnga.isParentOf(chit);
		kingShan.isParentOf(vich);
		queenAnga.isParentOf(vich);
		kingShan.isParentOf(satya);
		queenAnga.isParentOf(satya);

		chit.isSpouseOf(ambi);
		chit.isParentOf(drita);
		ambi.isParentOf(drita);
		chit.isParentOf(vrita);
		ambi.isParentOf(vrita);

		vich.isSpouseOf(lika);
		vich.isParentOf(vila);
		lika.isParentOf(vila);
		vich.isParentOf(chika);
		lika.isParentOf(chika);

		satya.isSpouseOf(vyan);
		satya.isParentOf(satvi);
		vyan.isParentOf(satvi);
		satya.isParentOf(savya);
		vyan.isParentOf(savya);
		satya.isParentOf(saayan);
		vyan.isParentOf(saayan);

		jaya.isSpouseOf(drita);
		jaya.isParentOf(jata);
		drita.isParentOf(jata);
		jaya.isParentOf(driya);
		drita.isParentOf(driya);

		vila.isSpouseOf(jinki);
		vila.isParentOf(lavnya);
		jinki.isParentOf(lavnya);

		chika.isSpouseOf(kpila);
		satvi.isSpouseOf(asva);

		savya.isSpouseOf(krpi);
		savya.isParentOf(kriya);
		krpi.isParentOf(kriya);

		saayan.isSpouseOf(mina);
		saayan.isParentOf(misa);
		mina.isParentOf(misa);

		driya.isSpouseOf(mnu);
		lavnya.isSpouseOf(gru);
	}
	
	
	@Test
	public void testLevels() {
		final Family family = new Family();
		Family.FamilyMember chit = family.addMember("chit", Gender.MALE);
		Family.FamilyMember drita = family.addMember("drita", Gender.MALE);
		Family.FamilyMember vrita = family.addMember("vrita", Gender.MALE);
		Family.FamilyMember ambi = family.addMember("ambi", Gender.FEMALE);
		chit.isSpouseOf(ambi);
		chit.isParentOf(drita);
		chit.isParentOf(vrita);
//		Assert.assertEquals(family.getAllFamilyMemberNames().size(), 4);
//		Assert.assertTrue(Relation.BROTHER.to(drita).contains(vrita));
		Assert.assertEquals(family.oldGenerationPerson(chit, vrita).getName(), chit.getName());
	}
	
	@Test
	public void testRelation() {
		final Family family = new Family();
		Family.FamilyMember kingShan = family.addMember("King Shan", Gender.MALE);
		Family.FamilyMember queenAnga = family.addMember("Queen Anga", Gender.FEMALE);
		Family.FamilyMember ish = family.addMember("ish", Gender.MALE);
		Family.FamilyMember chit = family.addMember("chit", Gender.MALE);
		Family.FamilyMember ambi = family.addMember("ambi", Gender.FEMALE);
		Family.FamilyMember drita = family.addMember("drita", Gender.MALE);
		kingShan.isSpouseOf(queenAnga);
		kingShan.isParentOf(ish);
		kingShan.isParentOf(chit);
		chit.isSpouseOf(ambi);
		chit.isParentOf(drita);
//		Assert.assertEquals(family.oldGenerationPerson(chit, vrita).getName(), chit.getName());
		Assert.assertEquals(family.getRelationShip(ish, drita), Relation.PATERNAL_UNCLE);

	}
}
