# MyContactsApp

A console-driven contact management system using the concepts of OOPS in java.

## [Use Case 1](https://github.com/Azaken1248/MyContactsApp/tree/feature/UC1-MyContacts)

To create an account with email, password, and profile information.

#### key concept

(1) Using Builder and Factory Patterns to create User objects ([UserBuilder](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC1-MyContacts/user/model/UserBuilder.java))

```java
public User build() {
    return UserFactory.createUser(this.userType, this);
}

```

(2) Validating user input using Regex ([UserValidator](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC1-MyContacts/user/validation/UserValidator.java))

```java
public static void validateEmail(String email) throws InvalidEmailException{
    if(email == null || email.isEmpty()) {
        throw new InvalidEmailException("Email cannot be empty!!");
    }
    
    Pattern pattern = Pattern.compile(EMAIL_REGEX);
    Matcher matcher = pattern.matcher(email);
    
    if(!matcher.matches()) {
        throw new InvalidEmailException("Email address format invalid please use valid format: user@example.com");
    }
}

```

(3) Hashing passwords securely before storage ([PasswordHasher](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC1-MyContacts/user/utilities/PasswordHasher.java))

```java
MessageDigest digest = MessageDigest.getInstance("SHA-256");
byte[] hashBytes = digest.digest(password.getBytes());

```

## [Use Case 2](https://github.com/Azaken1248/MyContactsApp/tree/feature/UC2-MyContacts)

To log in with credentials to access the contact list.

#### key concept

(1) Strategy pattern for flexible authentication algorithms ([Authentication](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC2-MyContacts/auth/Authentication.java))

```java
public interface Authentication {
    public Optional<User> authenticate(String identifier, String secret);
}

```

(2) Concrete implementation for Basic Authentication ([BasicAuthStrategy](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC2-MyContacts/auth/strategy/BasicAuthStrategy.java))

```java
public Optional<User> authenticate(String email, String rawPassword){
    User user = userDatabase.get(email);
    if(user == null) {
        return Optional.empty();
    }
    
    String hashed = hasher.hash(rawPassword);
    if(hashed.equals(user.getPasswordHash())) {
        return Optional.of(user);
    }
    return Optional.empty();
}

```

(3) Singleton session manager to hold the active user context ([SessionManager](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC2-MyContacts/auth/session/SessionManager.java))

```java
public static SessionManager getInstance() {
    if(instance == null) {
        instance = new SessionManager();
    }
    return instance;
}

```

## [Use Case 3](https://github.com/Azaken1248/MyContactsApp/tree/feature/UC3-MyContacts)

To update profile information, change password, or manage preferences.

#### key concept

(1) Command pattern to encapsulate profile update operations ([ProfileCommand](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC3-MyContacts/user/command/ProfileCommand.java))

```java
public interface ProfileCommand {
    void execute();
    void undo();
}

```

(2) Concrete command for updating user bio with undo functionality ([UpdateBioCommand](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC3-MyContacts/user/command/UpdateBioCommand.java))

```java
public void execute() {
    this.oldBio = profile.getBio();
    profile.setBio(newBio);
    System.out.println("Bio Updated...");
}

public void undo() {
    if(oldBio != null) {
        profile.setBio(oldBio);
        System.out.println("Reverted to old Bio...");
    }
}

```

(3) Controller to manage command execution and history stack ([ProfileUpdateController](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC3-MyContacts/user/command/ProfileUpdateController.java))

```java
public static void executeCommand(ProfileCommand profileCommand) {
    try {
        profileCommand.execute();
        commandHistory.push(profileCommand);
    } catch(Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}

public static void undoCommand() {
    if(commandHistory.isEmpty()) {
        return;
    }
    ProfileCommand previousCommand = commandHistory.pop();
    previousCommand.undo();
}

```

## [Use Case 4](https://github.com/Azaken1248/MyContactsApp/tree/feature/UC4-MyContacts)

To add a new contact with name, phone numbers, email addresses, and optional fields.

#### key concept

(1) Contact hierarchy abstracting common properties from concrete implementations ([Contact](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC4-MyContacts/contact/model/Contact.java))

```java
public abstract class Contact implements ContactComponent {
    private final UUID id;
    private final LocalDateTime createdAt;
    private String name;
    // ...
}

```

(2) Using the Builder pattern for constructing complex contact types ([Person](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC4-MyContacts/contact/model/Person.java))

```java
public static class PersonBuilder {
    String name;
    String relationship = "Aquaintance";
    private final List<PhoneNumber> phones = new ArrayList<>();
    private final List<EmailAddress> emails = new ArrayList<>();
    
    public Person build() {
        return ContactFactory.createPersonContact(this);
    }
}

```

## [Use Case 5](https://github.com/Azaken1248/MyContactsApp/tree/feature/UC5-MyContacts)

To view complete information of a specific contact.

#### key concept

(1) Decorator pattern to dynamically augment contact display representations ([ContactViewDecorator](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC5-MyContacts/contact/view/ContactViewDecorator.java))

```java
public class ContactViewDecorator implements ContactView {
    protected final ContactView view;
    protected final Contact contact;
    
    public ContactViewDecorator(ContactView view, Contact contact) {
        this.view = view;
        this.contact = contact;
    }
    
    @Override
    public String display() {
        return view.display();
    }
}

```

(2) Concrete decorator appending formatted metadata ([MetadataDecorator](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC5-MyContacts/contact/view/MetadataDecorator.java))

```java
public String display() {
    String baseDisplay = view.display();
    String timeAdded = contact.getTimeStamp().toString().substring(0, 16).replace("T", " ");
    
    return String.format(
            "=================================================\n" +
            "%s\n" +
            "Added: %s\n" +
            "ID: %s\n" +
            "=================================================",
            baseDisplay, timeAdded, contact.getId().toString());
}

```

## [Use Case 6](https://github.com/Azaken1248/MyContactsApp/tree/feature/UC6-MyContacts)

To modify existing contact information.

#### key concept

(1) Memento pattern to preserve the state of an object prior to mutation ([ContactMemento](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC6-MyContacts/contact/command/ContactMemento.java))

```java
public class ContactMemento {
    private final Contact savedState;
    
    public ContactMemento(Contact contactToSave) {
        this.savedState = contactToSave.copy();
    }
    
    public Contact getSavedState() {
        return savedState;
    }
}

```

(2) Command pattern combining edits with Memento states for reliable rollbacks ([EditContactCommand](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC6-MyContacts/contact/command/EditContactCommand.java))

```java
public void execute() {
    this.memento = new ContactMemento(addressBook.get(indexToEdit));
    addressBook.set(indexToEdit, editedContact);
    System.out.println("Contact Edited Successfully");
}

public void undo() {
    if(memento != null) {
        addressBook.set(indexToEdit, memento.getSavedState());
        System.out.println("Rolled back contact Edit");
    }
}

```

## [Use Case 7](https://github.com/Azaken1248/MyContactsApp/tree/feature/UC7-MyContacts)

To remove a contact from the list with confirmation.

#### key concept

(1) Observer pattern to broadcast deletion events across the system ([ContactObserver](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC7-MyContacts/contact/observer/ContactObserver.java))

```java
public interface ContactObserver {
    void onContactDeleted(Contact contact, boolean isHardDelete);
}

```

(2) Concrete observer for system logging of lifecycle events ([DeletionLogger](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC7-MyContacts/contact/observer/DeletionLogger.java))

```java
public void onContactDeleted(Contact contact, boolean isHardDelete) {
    String type = isHardDelete ? "HARD DELETED (Permanent)" : "SOFT DELETED (Move to trash)";
    System.out.println("\n[SYSTEM LOG] Lifecycle Event: Contact '" + contact.getName() + "' was " + type + ".");
}

```

(3) Method implementation to clear aggregated objects upon deletion ([Contact](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC7-MyContacts/contact/model/Contact.java))

```java
public void cascadeDelete() {
    this.phoneNumbers.clear();
    this.emailAddresses.clear();
}

```

## [Use Case 8](https://github.com/Azaken1248/MyContactsApp/tree/feature/UC8-MyContacts)

To perform operations on multiple contacts.

#### key concept

(1) Composite pattern enabling uniform treatment of individual entities and collections ([ContactGroup](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC8-MyContacts/contact/composite/ContactGroup.java))

```java
public class ContactGroup implements ContactComponent {
    private final List<ContactComponent> components = new ArrayList<>();
    
    @Override
    public void addTag(Tag tag) {
        components.forEach(c -> c.addTag(tag));
    }
    
    @Override
    public String exportToCSV() {
        return components.stream()
                         .map(ContactComponent::exportToCSV)
                         .collect(Collectors.joining("\n"));
    }
}

```

(2) Executing recursive structural operations using the Stream API ([ContactGroup](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC8-MyContacts/contact/composite/ContactGroup.java))

```java
public void performBulkSoftDelete(User actveUser){
    new ArrayList<>(components).forEach(c -> c.performBulkSoftDelete(actveUser));
}

```

## [Use Case 9](https://github.com/Azaken1248/MyContactsApp/tree/feature/UC9-MyContacts)

To search contacts by name, phone, email, or tags.

#### key concept

(1) Specification pattern extending Predicate for querying ([SearchCriteria](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC9-MyContacts/contact/search/SearchCriteria.java))

```java
public interface SearchCriteria extends Predicate<Contact>{
    // inherited boolean test(Contact t) method from predicate
}

```

(2) Implementation of criteria via case-insensitive regex evaluation ([NameCriteria](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC9-MyContacts/contact/search/NameCriteria.java))

```java
public class NameCriteria implements SearchCriteria{
    private final Pattern pattern;
    
    public NameCriteria(String searchQuery) {
        this.pattern = Pattern.compile(Pattern.quote(searchQuery), Pattern.CASE_INSENSITIVE);
    }
    
    @Override
    public boolean test(Contact contact) {
        return pattern.matcher(contact.getName()).find();
    }
}

```

(3) Functional processing through stream filters ([MyContactsApp](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC9-MyContacts/main/MyContactsApp.java))

```java
List<Contact> results = contacts.stream()
        .filter(criteria)
        .collect(Collectors.toList());

```

## [Use Case 10](https://github.com/Azaken1248/MyContactsApp/tree/feature/UC10-MyContacts)

To apply multiple filters by tag, date added, and frequently contacted.

#### key concept

(1) Chain of Responsibility pattern mapping compound logical conditions ([AndCriteria](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC10-MyContacts/contact/search/AndCriteria.java))

```java
public class AndCriteria implements SearchCriteria{
    private final List<SearchCriteria> criteriaChain;
    
    public AndCriteria(List<SearchCriteria> criteriaChain) {
        this.criteriaChain = criteriaChain;
    }
    
    @Override
    public boolean test(Contact contact) {
        for(SearchCriteria criteria : criteriaChain) {
            if(!criteria.test(contact)) {
                return false;
            }
        }
        return true;
    }
}

```

(2) Strategy pattern governing distinct sorting algorithms ([ContactSortStrategy](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC10-MyContacts/contact/sort/ContactSortStrategy.java))

```java
public interface ContactSortStrategy {
    void sort(List<Contact> contacts);
}

```

(3) Implementation comparing domain-specific fields ([SortByDateStrategy](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC10-MyContacts/contact/sort/SortByDateStrategy.java))

```java
public void sort(List<Contact> contacts) {
    contacts.sort(Comparator.comparing(Contact::getTimeStamp).reversed());
}

```

## [Use Case 11](https://github.com/Azaken1248/MyContactsApp/tree/feature/UC11-MyContacts)

To create custom tags for organizing contacts.

#### key concept

(1) Flyweight pattern to centralize instances and eliminate redundancy in memory ([TagFactory](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC11-MyContacts/contact/tag/TagFactory.java))

```java
public class TagFactory {
    private static final Map<String, Tag> tagPool = new HashMap<>();
    
    public static Tag getTag(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tag cannot be empty");
        }
        
        String normalizedName = name.trim().toUpperCase();
        return tagPool.computeIfAbsent(normalizedName, Tag::new);
    }
}

```

(2) Overriding equals and hashcode to ensure hashing reliability for sets ([Tag](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC11-MyContacts/contact/tag/Tag.java))

```java
@Override
public boolean equals(Object o) {
    if(this == o) return true;
    if(o == null || getClass() != o.getClass()) return false;
    
    Tag tag = (Tag) o;
    return name.equals(tag.name);
}

@Override
public int hashCode() {
    return Objects.hash(name);
}

```

(3) Seeding predefined classifications leveraging EnumSet ([TagFactory](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC11-MyContacts/contact/tag/TagFactory.java))

```java
static {
    EnumSet<PredefinedTag> defaultTags = EnumSet.allOf(PredefinedTag.class);
    for(PredefinedTag tag : defaultTags) {
        tagPool.put(tag.name(), new Tag(tag.name()));
    }
}

```

## [Use Case 12](https://github.com/Azaken1248/MyContactsApp/tree/feature/UC12-MyContacts)

To assign one or multiple tags to contacts.

#### key concept

(1) Association class constructing a many-to-many link ([TagAssignment](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC12-MyContacts/contact/tag/TagAssignment.java))

```java
public class TagAssignment {
    private final Contact contact;
    private final Tag tag;
    private final LocalDateTime assignedAt;
    
    public TagAssignment(Contact contact, Tag tag) {
        this.contact = contact;
        this.tag = tag;
        this.assignedAt = LocalDateTime.now();
    }
}

```

(2) Bidirectional mapping management dictating the relationship flow ([Contact](https://github.com/Azaken1248/MyContactsApp/blob/feature/UC12-MyContacts/contact/model/Contact.java))

```java
@Override
public void addTag(Tag tag) {
    TagAssignment assignment = new TagAssignment(this, tag);
    
    if (this.tagAssignments.add(assignment)) { 
        tag.addContactLink(this);
    }
}

```

## Integration Pipeline

Each feature is developed on a seperate branch and after testing it is

moved to [dev](https://github.com/Azaken1248/MyContactsApp/tree/dev)

after which it is finally released to [main](https://github.com/Azaken1248/MyContactsApp)
