sentence(sentence(N, V)) --> noun_phrase(N), verb_phrase(V).
sentence(sentence(N, V)) --> noun_phrase_p(N), verb_phrase_p(V).

sentence(sentence(N)) --> noun_phrase(N).
sentence(sentence(N)) --> noun_phrase_p(N).


noun_phrase(noun_phrase(DET,N)) --> determinant_f(DET), noun_f(N).
noun_phrase(noun_phrase(DET,N)) --> determinant_m(DET), noun_m(N).
noun_phrase_p(noun_phrase_p(DET,N)) --> determinant_p_f(DET), noun_p_f(N).
noun_phrase_p(noun_phrase_p(DET,N)) --> determinant_p_m(DET), noun_p_m(N).

verb_phrase(verb_phrase(V,P,DET,N)) --> verb(V), preposition(P), determinant_f(DET), noun_f(N).
verb_phrase(verb_phrase(V,P,DET,N)) --> verb(V), preposition(P), determinant_m(DET), noun_m(N).

verb_phrase(verb_phrase(V,P,DET,N)) --> verb(V), preposition(P), determinant_p_f(DET), noun_p_f(N).
verb_phrase(verb_phrase(V,P,DET,N)) --> verb(V), preposition(P), determinant_p_m(DET), noun_p_m(N).

verb_phrase_p(verb_phrase_p(V,P,DET,N)) --> verb_p(V), preposition(P), determinant_f(DET), noun_f(N).
verb_phrase_p(verb_phrase_p(V,P,DET,N)) --> verb_p(V), preposition(P), determinant_m(DET), noun_m(N).

verb_phrase_p(verb_phrase_p(V,P,DET,N)) --> verb_p(V), preposition(P), determinant_p_f(DET), noun_p_f(N).
verb_phrase_p(verb_phrase_p(V,P,DET,N)) --> verb_p(V), preposition(P), determinant_p_m(DET), noun_p_m(N).

verb_phrase(verb_phrase(V,DET,N)) --> verb(V), determinant_f(DET), noun_f(N).
verb_phrase(verb_phrase(V,DET,N)) --> verb(V), determinant_m(DET), noun_m(N).
verb_phrase(verb_phrase(V,DET,N)) --> verb(V), determinant_p_f(DET), noun_p_f(N).
verb_phrase(verb_phrase(V,DET,N)) --> verb(V), determinant_p_m(DET), noun_p_m(N).

verb_phrase_p(verb_phrase_p(V,DET,N)) --> verb_p(V), determinant_f(DET), noun_f(N).
verb_phrase_p(verb_phrase_p(V,DET,N)) --> verb_p(V), determinant_m(DET), noun_m(N).
verb_phrase_p(verb_phrase_p(V,DET,N)) --> verb_p(V), determinant_p_f(DET), noun_p_f(N).
verb_phrase_p(verb_phrase_p(V,DET,N)) --> verb_p(V), determinant_p_m(DET), noun_p_m(N).

verb_phrase(verb_phrase(V,C,N)) --> verb(V), contraction_f(C), noun_f(N).
verb_phrase(verb_phrase(V,C,N)) --> verb(V), contraction_m(C), noun_m(N).

verb_phrase_p(verb_phrase_p(V,C,N)) --> verb_p(V), contraction_f(C), noun_f(N).
verb_phrase_p(verb_phrase_p(V,C,N)) --> verb_p(V), contraction_m(C), noun_m(N).

verb_phrase(verb_phrase(V)) --> verb(V).
verb_phrase_p(verb_phrase_p(V)) --> verb_p(V).


%nomes femininos singular

noun_f(noun_f(menina)) --> [menina].
noun_f(noun_f(floresta)) --> [floresta].
noun_f(noun_f(mae)) --> [mae].
noun_f(noun_f(vida)) --> [vida].

noun_f(noun_f(noticia))  --> [noticia].
noun_f(noun_f(cidade)) --> [cidade].
noun_f(noun_f(porta)) --> [porta].

%nomes femininos plural

noun_p_f(noun_p_f(lagrimas)) --> [lagrimas].
noun_p_f(noun_p_f(meninas))  --> [meninas].

%nomes masculinos singular

noun_m(noun_m(tempo)) --> [tempo].
noun_m(noun_m(cacador)) --> [cacador].
noun_m(noun_m(rosto)) --> [rosto].
noun_m(noun_m(rio)) --> [rio].
noun_m(noun_m(mar)) --> [mar].
noun_m(noun_m(vento)) --> [vento].
noun_m(noun_m(martelo)) --> [martelo].
noun_m(noun_m(cachorro)) --> [cachorro].
noun_m(noun_m(tambor)) --> [tambor].
noun_m(noun_m(sino)) --> [sino].

%nomes masculinos plural

noun_p_m(noun_p_m(lobos)) --> [lobos].
noun_p_m(noun_p_m(tambores)) --> [tambores].

%verbos singular

verb(verb(corre)) --> [corre].
verb(verb(correu)) -->  [correu].
verb(verb(bateu)) --> [bateu].

%verbos plural

verb_p(verb_p(corriam)) --> [corriam].
verb_p(verb_p(bateram)) --> [bateram].
verb_p(verb_p(correram)) --> [correram].

%determinante feminino singular

determinant_f(determinant_f('A')) --> ['A'].
determinant_f(determinant_f(a)) --> [a].

%determinante feminino plural

determinant_p_f(determinant_p_f('As')) --> ['As'].

%determinante masculino singular

determinant_m(determinant_m('O')) --> ['O'].
determinant_m(determinant_m(o)) --> [o].

%determinante masculino plural

determinant_p_m(determinant_p_m('Os')) --> ['Os'].
determinant_p_m(determinant_p_m(os)) --> [os].

%preposicao

preposition(preposition(para)) --> [para].
preposition(preposition(com)) --> [com].

%conjuncao feminino
contraction_f(contraction_f(pela)) --> [pela].
contraction_f(contraction_f(na)) --> [na].

%conjuncao masculino

contraction_m(contraction_m(pelo)) --> [pelo].
contraction_m(contraction_m(no)) --> [no].




