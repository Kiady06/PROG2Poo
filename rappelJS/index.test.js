import { expect } from "chai";
import { describe, it } from "mocha";
import { countAdults } from "./age.js";
import { getIvandryCount } from "./getIvandryCount.js";
import { getRetakeExams } from "./getRetakeExams.js";

describe("Count age", () => {
it("should return 0 when all values are under 18", () => {
    expect(countAdults(1, 5, 10, 17)).to.eq(0);
});
it("should correctly count when all are adults", () => {
  expect(countAdults(18, 25, 40)).to.eq(3);
});
  it("should return 3 for this tab : [18, 10, 12, 25, 33] ", () => {
    expect(countAdults([18, 10, 12, 25, 33])).to.eq(3);
  });
  it("should return 3 for these numbers : 18, 10, 12, 25, 33 ", () => {
    expect(countAdults(18, 10, 12, 25, 33)).to.eq(3);
  });
  it("should return 0 for this tab : [] ", () => {
    expect(countAdults([])).to.eq(0);
  });
  it("should return 0 for a null argument ", () => {
    expect(countAdults()).to.eq(0);
  });
  it("should return 1 for this number : 18 ", () => {
    expect(countAdults(18)).to.eq(1);
  });
  it("should return 1 for this one element tab : [18] ", () => {
    expect(countAdults([18])).to.eq(1);
  });
  it("should return an error if we have other than a positive integer as parameter ", () => {
    const invalidInputs = [
      [1, "Meh", 7],
      [1, -67, 7],
      [1, 6.7, 7],
      ["Meh meh"],
    ];

    invalidInputs.forEach((input) => {
      expect(() => countAdults(input)).to.throw(Error,
        "Input should only contain positive integers",
      );
    });
  });
});

describe("Get Ivandry Count", () => {
  
  it("should return 2 for the list: ['Tennis Ivandry', 'La City Ivandry', 'Mausolée']", () => {
    const input = ["Tennis Ivandry", "La City Ivandry", "Mausolée"];
    expect(getIvandryCount(input)).to.eq(2);
  });

  it("should be case-insensitive (should count 'IVANDRY' or 'ivandry')", () => {
    expect(getIvandryCount("IVANDRY", "ivandry", "Ivandry")).to.eq(3);
  });

  it("should return 0 if 'Ivandry' is not present in the strings", () => {
    expect(getIvandryCount("Analamahitsy", "Mausolée", "Ambohitrarahaba")).to.eq(0);
  });

  it("should return 0 for an empty array or no arguments", () => {
    expect(getIvandryCount([])).to.eq(0);
    expect(getIvandryCount()).to.eq(0);
  });

  it("should correctly count when strings are passed as separate arguments (Rest parameter)", () => {
    expect(getIvandryCount("Hôtel Ivandry", "Chez Ivandry", "Antanimena")).to.eq(2);
  });

  it("should return 1 for a single string containing 'Ivandry'", () => {
    expect(getIvandryCount("Ivandry")).to.eq(1);
  });

  it("should not count words containing 'Ivandry' as a subpart (ex: 'Ivandryland')", () => {
    expect(getIvandryCount("Ivandryland")).to.eq(0);
  });

  it("should throw an error if one of the elements is not a string", () => {
    const invalidInputs = [
      [123, "Ivandry"],
      ["Ivandry", null],
      ["Ivandry", { loc: "Ivandry" }],
      [true]
    ];

    invalidInputs.forEach((input) => {
      expect(() => getIvandryCount(input)).to.throw(Error, "Inputs should be strings");
    });
  });

});

describe("Get Retake Exams", () => {

  it("should return an empty array when all grades are passing (>=10)", () => {
    const student = {
      first_name : Krysten,
      last_name : Comerightback,
      grades: {
        math: 12,
        english: 15,
        science: 10
      }
    };

    expect(getRetakeExams(student)).to.deep.eq([]);
  });

  it("should return all subjects when all grades are failing (<10)", () => {
    const student = {
      first_name : Krysten,
      last_name : Comerightback,
      grades: {
        math: 5,
        english: 8,
        science: 3
      }
    };

    expect(getRetakeExams(student)).to.deep.eq(["math", "english", "science"]);
  });

  it("should return only subjects with grades under 10", () => {
    const student = {
      first_name : Krysten,
      last_name : Comerightback,
      grades: {
        math: 12,
        english: 7,
        science: 9,
        history: 14
      }
    };

    expect(getRetakeExams(student)).to.deep.eq(["english", "science"]);
  });

  it("should return an empty array when grades object is empty", () => {
    const student = {
      first_name : Krysten,
      last_name : Comerightback,
      grades: {}
    };

    expect(getRetakeExams(student)).to.deep.eq([]);
  });

  it("should correctly handle borderline value 10 (passing grade)", () => {
    const student = {
      first_name : Krysten,
      last_name : Comerightback,
      grades: {
        math: 10,
        english: 9,
        science: 11
      }
    };

    expect(getRetakeExams(student)).to.deep.eq(["english"]);
  });

  it("should work with a single subject failing", () => {
    const student = {
      first_name : Krysten,
      last_name : Comerightback,
      grades: {
        math: 6.5
      }
    };

    expect(getRetakeExams(student)).to.deep.eq(["math"]);
  });

});