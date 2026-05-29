# 🏋️‍♂️ Katalog Suplementů & AI Poradce

ℹ️ **O čem aplikace je (Overview)**

Trh se sportovní výživou je dnes obrovský, nepřehledný a plný agresivního marketingu. Začínající i pokročilí sportovci se často ztrácejí ve složitých názvech, zázračných slibech a protichůdných informacích z internetu. Tato aplikace vznikla s jasným cílem: **vytvořit ultimátního, vědecky podloženého průvodce světem suplementů.**

Nesnaží se vám nic prodat. Místo toho funguje jako interaktivní encyklopedie, která poskytuje čistá a objektivní data. Ať už je vaším cílem budování hrubé síly a svalové hmoty, maximalizace soustředění a dynamiky pro náročné sportovní výkony, nebo hluboká regenerace a ochrana kloubů po těžkých trénincích, zde najdete odpovědi. Aplikace detailně a srozumitelně vysvětluje, jak jednotlivé látky v těle reálně fungují, jaké je jejich optimální dávkování, vyvrací běžné mýty a upozorňuje na možná zdravotní rizika.

Aby byl zážitek naprosto komplexní, je v aplikaci integrovaný **AI Poradce** (poháněný umělou inteligencí). Ten funguje jako váš osobní trenér v kapse – stačí mu napsat váš aktuální problém nebo cíl (např. *"Jsem vyčerpaný po závodě a bolí mě kolena, co si mám dát?"*) a umělá inteligence vám na míru sestaví doporučení z produktů dostupných v katalogu.

🌟 **Technické Highlights**

*   **Dynamická databáze:** Všechny produkty, encyklopedické texty i obrázky se načítají čistě z externího souboru `suplementy.json`.
*   **Chytrý AI Poradce:** Integrované Google Gemini API, které radí uživatelům na míru a automaticky zná všechny aktuálně dostupné produkty v katalogu.
*   **Čisté OOP:** Plné využití dědičnosti, abstraktních tříd a rozhraní (Interface) pro snadnou budoucí rozšiřitelnost.
*   **Moderní GUI:** Responzivní design s velkými grafickými dlaždicemi (GridBagLayout), dynamické škálování obrázků a intuitivní ovládání.

⌛ **Jak to spustit (Quick Start)**

*Zajímá vás kód nebo si chcete aplikaci rovnou vyzkoušet? Tady je návod, jak ji rozběhnout za méně než minutu:*

1. Naklonujte si tento repozitář.
2. Ujistěte se, že máte v projektu přidanou knihovnu **Gson** (pro parsování JSON souborů).
3. V kořenové složce projektu vytvořte textový soubor `config.properties` a vložte do něj svůj API klíč:
   `API_KEY=vas_google_gemini_klic`
4. Spusťte třídu `Main.java` a aplikace se otevře maximalizovaná přes celou obrazovku!

*(Poznámka: Pro správné zobrazení produktů musí být složka `images/` a soubor `suplementy.json` ve stejném adresáři jako spouštěcí soubor).*

✍️ **Autor**

Aplikace vznikla jako ucelený softwarový projekt během 2. ročníku na SPŠE Ječná. Mým cílem bylo nejen splnit programátorské zadání na práci se soubory a síťovým API, ale vytvořit plně funkční a designově čistou aplikaci, která má reálné využití a dokáže skutečně pomoci s orientací ve fitness světě.