import { expect } from "chai";
import { describe, it  } from "mocha";
import { add } from "./index.js"

// decrire les groupes tests
describe("Addition", () => {
    // decrire chaque test individuel
    it("devrait retourner 4 si on demande 2 + 2", () => {
        expect(add(2, 2)).to.eq(4);
    });
    it("devrait marcher avec des nombres negatifs", () => {
        expect(add(-2, -2)).to.eq(-4);
        expect(add(-10, +10)).to.eq(0);
    });
    it("quand il manque un argument", () => {
        expect(add(2)).to.eq(2);
    });
    it("quand il manque deux arguments", () => {
        expect(() => add()).to.throw("Erreur: veuillez definir au moins a ou b");
    });
});
