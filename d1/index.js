export const add = (a, b) => {
    if (b == undefined || b == null ) {
        if (undefined == a || null == a) {throw Error("Erreur: veuillez definir au moins a ou b");}
        return a; 
    }
    return a + b;
};