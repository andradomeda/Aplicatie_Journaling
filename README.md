Aplicație de Journaling - Proiect Android

Descriere: 

Această aplicație de journaling este un proiect Android realizat în Java pentru a exersa dezvoltarea aplicațiilor mobile în Android Studio. Aplicația permite utilizatorilor să scrie intrări zilnice, să salveze aceste intrări într-o bază de date locală, să vizualizeze intrările din calendar și să aleagă starea lor emoțională folosind un spinner. De asemenea, aplicația include funcționalități de autentificare, o pagină principală de navigare și o secțiune de review și "about us".

Tehnologii Utilizate:

Android Studio: IDE-ul utilizat pentru dezvoltarea aplicației.
Java: Limbajul de programare folosit pentru logica aplicației.
Room Database: Utilizat pentru stocarea datelor în aplicație.
Custom Adapter: Utilizate pentru a crea și gestiona lista de intrări.

Structura Proiectului
1. Autentificare:
Pagina care permite utilizatorului să se logheze în aplicație și să acceseze funcționalitățile sale.

2. MainActivity:
Pagina principală a aplicației care direcționează utilizatorii către diferite secțiuni: calendar, entry și altele.

3. CalendarActivity:
Afișează un calendar interactiv și permite utilizatorului să selecteze o dată pentru a vizualiza intrările corespunzătoare.

4. EntryActivity:
Permite utilizatorului să adauge o nouă intrare în jurnal, selectând și un mood dintr-un spinner. Intrările sunt stocate în Room Database.

5. Database (Room Database):
Folosește Room pentru a crea entități care stochează intrările utilizatorilor și stările lor emoționale. Permite adăugarea, actualizarea și vizualizarea intrărilor.

6. Adapter Personalizat:
Un adapter personalizat pentru a popula RecyclerView cu intrările din baza de date.
