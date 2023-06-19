$(document).ready(function() {
    // Dummy data
    var usersData = [];
    var productsData = [];

    var usersTable;
    var productsTable;
    var productsResult = $('#searchResultsTableProducts').DataTable({
        data: [],
        columns: [
            { data: 'productname' },
            { data: 'seller' },
            {
                data: 'price',
                render: function(data) {
                    var formattedPrice = parseFloat(data).toFixed(2);
                    return formattedPrice;
                },
            },
            {
                data: null,
                orderable: false,
                render: function(data) {
                    return '<button id="productId-' + data.id + '" class="btn btn-outline-secondary">&minus;</button>';
                },
            },
        ],
        searching: false,
        paging: false,
        info: false,
    });
    var usersResults = $('#searchResultsTableUsers').DataTable({
        data: [],
        columns: [
            { data: 'username' },
            { data: 'email' },
            {
                data: null,
                orderable: false,
                render: function(data) {
                    return '<button id="userId-' + data.id + '" class="btn btn-outline-secondary">&minus;</button>';
                },
            },
        ],
        searching: false,
        paging: false,
        info: false,
    });

    // Fetch all users
    fetch('http://localhost:8080/users')
        .then(response => response.json())
        .then(data => {
            usersData = data;
            populateUsersTable(usersTable);
        })
        .catch(error => {
            console.error('Error fetching users:', error);
        });

    // Fetch all products
    fetch('http://localhost:8080/products')
        .then(response => response.json())
        .then(data => {
            productsData = data;
            populateProductsTable(productsTable);
        })
        .catch(error => {
            console.error('Error fetching products:', error);
        });

    // Populate users table
    function populateUsersTable(table) {
        table = $('#usersTable').DataTable({
            data: usersData,
            columns: [
                { data: 'username' },
                { data: 'email' },
                {
                    data: null,
                    orderable: false,
                    render: function(data) {
                        return '<button id="userId-' + data.id + '" class="btn btn-outline-secondary">&minus;</button>';
                    },
                },
            ],
            searching: false,
            paging: false,
            info: false,
        });
    }

    // Populate products table
    function populateProductsTable(table) {
        table = $('#productsTable').DataTable({
            data: productsData,
            columns: [
                { data: 'productname' },
                { data: 'seller' },
                {
                    data: 'price',
                    render: function(data) {
                        var formattedPrice = parseFloat(data).toFixed(2);
                        return formattedPrice;
                    },
                },
                {
                    data: null,
                    orderable: false,
                    render: function(data) {
                        return '<button id="productId-' + data.id + '" class="btn btn-outline-secondary">&minus;</button>';
                    },
                },
            ],
            searching: false,
            paging: false,
            info: false,
        });
    }

    // Tab link click event
    $('#searchTabBar .nav-link').on('click', function(e) {
        e.preventDefault();
        $('#searchTabBar .nav-link').removeClass('active');
        $(this).addClass('active');

        var targetTab = $(this).data('tab');
        $('.tab-pane').removeClass('active show');
        $('.tab-pane').hide();
        $('#tab-' + targetTab).addClass('active show');
        $('#tab-' + targetTab).show();
    });

    // Add User button click event
    $('#addUserButton').click(function() {
        // Fetch input values
        var username = $('#usernameInput').val();
        var email = $('#emailInput').val();
        var password = $('#passwordInput').val();
        var repeatPassword = $('#repeatPasswordInput').val();

        // Perform validation and add user
        if (!validateEmail(email)) {
            alert('Please enter a valid email address.');
            return;
        }

        if (password !== repeatPassword) {
            alert('Passwords do not match.');
            return;
        }

        // Create user object
        var newUser = {
            username: username,
            email: email,
            password: password,
        };

        // Send POST request to create user
        fetch('http://localhost:8080/users', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(newUser),
        })
            .then(response => {
                if(response.ok) {
                    alert('User created successfully.');
                    usersData.push(newUser);
                    usersTable.row.add(newUser).draw();
                }
            })
            .catch(error => {
                console.error('Error creating user:', error);
                alert('An error occurred while creating the user.');
            });
    });

    // Add Product button click event
    $('#addProductButton').click(function() {
        // Fetch input values
        var productName = $('#productNameInput').val();
        var seller = $('#sellerInput').val();
        var price = parseFloat($('#priceInput').val());

        // Perform validation and add product
        if (price < 0) {
            alert('Price must be zero or above.');
            return;
        }

        // Create product object
        var newProduct = {
            productname: productName,
            seller: seller,
            price: price,
        };

        // Send POST request to create product
        fetch('http://localhost:8080/products', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(newProduct),
        })
            .then(response => {
                if(response.ok) {
                    alert('Product created successfully.');
                    productsData.push(newProduct);
                    productsTable.row.add(newProduct).draw();
                }
            })
            .catch(error => {
                console.error('Error creating product:', error);
                alert('An error occurred while creating the product.');
            });
    });

    // User Search button click event
    $('#searchButtonUsers').click(function() {
        var filter = $('input[name="userFilter"]:checked').val();
        var searchValue = $('#userSearchInput').val().toLowerCase();

        var filteredUsers = usersData.filter(function(user) {
            if (filter === 'username') {
                return user.username.toLowerCase().includes(searchValue);
            } else if (filter === 'email') {
                return user.email.toLowerCase().includes(searchValue);
            }
        });

        showSearchResults(usersResults, filteredUsers);
    });

    // Product Search button click event
    $('#searchButtonProducts').click(function() {
        var filter = $('input[name="productFilter"]:checked').val();
        var searchValue = $('#productSearchInput').val().toLowerCase();
        var minPrice = parseFloat($('#minPriceInput').val());
        var maxPrice = parseFloat($('#maxPriceInput').val());

        var filteredProducts = productsData.filter(function(product) {
            if (filter === 'product') {
                return product.productname.toLowerCase().includes(searchValue);
            } else if (filter === 'seller') {
                return product.seller.toLowerCase().includes(searchValue);
            }
        });

        if (!isNaN(minPrice)) {
            filteredProducts = filteredProducts.filter(function(product) {
                return product.price >= minPrice;
            });
        }

        if (!isNaN(maxPrice)) {
            filteredProducts = filteredProducts.filter(function(product) {
                return product.price <= maxPrice;
            });
        }

        showSearchResults(productsResult, filteredProducts);
    });

    // Populate search results table
    function showSearchResults(table, searchData) {
        table.clear();
        table.rows.add(searchData);
        table.draw();
    }

    // Password strength progress bar
    $('#passwordInput').on('input', function() {
        var password = $(this).val();
        var strength = calculatePasswordStrength(password);

        $('#passwordStrengthBar')
            .css('width', strength + '%')
            .attr('aria-valuenow', strength);
    });

    // Password strength calculation
    function calculatePasswordStrength(password) {
        var strength = 0;

        if (password.length >= 8) {
            strength += 30;
        }

        if (/[A-Z]/.test(password)) {
            strength += 30;
        }

        if (/[0-9]/.test(password)) {
            strength += 30;
        }

        if (/[!@#$%^&*]/.test(password)) {
            strength += 10;
        }

        return strength;
    }

    // Email validation
    function validateEmail(email) {
        var emailRegex = /\S+@\S+\.\S+/;
        return emailRegex.test(email);
    }
});
