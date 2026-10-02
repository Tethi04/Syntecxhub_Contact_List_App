let contacts = JSON.parse(localStorage.getItem('pastel_contacts')) || [
    { id: '1', name: 'Anjali Kumari Singh', phone: '+91 98123 45678', email: 'anjali@example.com', color: '#C084FC' },
    { id: '2', name: 'Kajal Yadav', phone: '+91 98234 56789', email: 'kajal@example.com', color: '#F472B6' },
    { id: '3', name: 'Tanushree Das', phone: '+91 98345 67890', email: 'tanushree@example.com', color: '#A5F3FC' }
];

let selectedContactId = null;

const contactListEl = document.getElementById('contactList');
const searchInput = document.getElementById('searchInput');

const addModal = document.getElementById('addModal');
const detailModal = document.getElementById('detailModal');

document.getElementById('openAddBtn').onclick = () => addModal.classList.remove('hidden');
document.getElementById('closeAddBtn').onclick = () => addModal.classList.add('hidden');
document.getElementById('closeDetailBtn').onclick = () => detailModal.classList.add('hidden');

function renderContacts(filter = '') {
    contactListEl.innerHTML = '';
    const filtered = contacts.filter(c => 
        c.name.toLowerCase().includes(filter.toLowerCase()) || c.phone.includes(filter)
    );

    filtered.forEach(c => {
        const item = document.createElement('div');
        item.className = 'contact-item';
        item.onclick = () => openDetail(c.id);
        item.innerHTML = `
            <div class="avatar" style="background:${c.color}">${c.name.charAt(0)}</div>
            <div class="info">
                <div class="name">${c.name}</div>
                <div class="phone">${c.phone}</div>
            </div>
        `;
        contactListEl.appendChild(item);
    });
}

function openDetail(id) {
    selectedContactId = id;
    const c = contacts.find(item => item.id === id);
    if (!c) return;

    document.getElementById('detailAvatar').innerText = c.name.charAt(0);
    document.getElementById('detailAvatar').style.background = c.color;
    document.getElementById('detailName').innerText = c.name;
    document.getElementById('detailPhone').innerText = c.phone;
    document.getElementById('detailEmail').innerText = c.email;

    document.getElementById('callBtn').href = `tel:${c.phone}`;
    document.getElementById('smsBtn').href = `sms:${c.phone}`;

    detailModal.classList.remove('hidden');
}

document.getElementById('saveContactBtn').onclick = () => {
    const name = document.getElementById('addName').value.trim();
    const phone = document.getElementById('addPhone').value.trim();
    const email = document.getElementById('addEmail').value.trim();

    if (name && phone) {
        const colors = ['#C084FC', '#F472B6', '#818CF8', '#A5F3FC'];
        const newContact = {
            id: Date.now().toString(),
            name,
            phone,
            email: email || 'contact@example.com',
            color: colors[Math.floor(Math.random() * colors.length)]
        };
        contacts.unshift(newContact);
        saveAndRender();
        addModal.classList.add('hidden');
        document.getElementById('addName').value = '';
        document.getElementById('addPhone').value = '';
        document.getElementById('addEmail').value = '';
    }
};

document.getElementById('deleteBtn').onclick = () => {
    if (selectedContactId) {
        contacts = contacts.filter(c => c.id !== selectedContactId);
        saveAndRender();
        detailModal.classList.add('hidden');
    }
};

searchInput.oninput = (e) => renderContacts(e.target.value);

function saveAndRender() {
    localStorage.setItem('pastel_contacts', JSON.stringify(contacts));
    renderContacts(searchInput.value);
}

// Initial Render
renderContacts();
