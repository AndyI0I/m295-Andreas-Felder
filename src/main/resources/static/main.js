$(document).ready(function() {
    // Dummy data
    var usersData = [];
    var productsData = [];

    // Fetch all users
    fetch('http://localhost:8080/users')
        .then(response => response.json())
        .then(data => {
            usersData = data;
            populateUsersTable();
        })
        .catch(error => {
            console.error('Error fetching users:', error);
        });

    // Fetch all products
    fetch('http://localhost:8080/products')
        .then(response => response.json())
        .then(data => {
            productsData = data;
            populateProductsTable();
        })
        .catch(error => {
            console.error('Error fetching products:', error);
        });

    // Populate users table
    function populateUsersTable() {
        var usersTable = $('#usersTable').DataTable({
            data: usersData,
            columns: [
                { data: 'username' },
                { data: 'email' },
                { data: null, orderable: false, render: function(data) {
                        return '<button class="btn btn-outline-secondary">&minus;</button>';
                    }}
            ],
            searching: false,
            paging: false,
            info: false
        });
    }

    // Populate products table
    function populateProductsTable() {
        var productsTable = $('#productsTable').DataTable({
            data: productsData,
            columns: [
                { data: 'productname' },
                { data: 'seller' },
                { data: 'price', render: function(data) {
                        var formattedPrice = parseFloat(data).toFixed(2);
                        var displayPrice = formattedPrice.substring(0, 4);
                        return displayPrice;
                    }},
                { data: null, orderable: false, render: function(data) {
                        return '<button class="btn btn-outline-secondary">&minus;</button>';
                    }}
            ],
            searching: false,
            paging: false,
            info: false
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
        // ...

        // Clear input fields
        $('#usernameInput').val('');
        $('#emailInput').val('');
        $('#passwordInput').val('');
        $('#repeatPasswordInput').val('');
    });

    // Add Product button click event
    $('#addProductButton').click(function() {
        // Fetch input values
        var productName = $('#productNameInput').val();
        var seller = $('#sellerInput').val();
        var price = parseFloat($('#priceInput').val());

        // Perform validation and add product
        // ...

        // Clear input fields
        $('#productNameInput').val('');
        $('#sellerInput').val('');
        $('#priceInput').val('');
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
        console.log(filteredUsers);
        populateSearchResultsUsersTable(filteredUsers);
    });

    // Product Search button click event
    $('#searchButtonProducts').click(function() {
        var filter = $('input[name="productFilter"]:checked').val();
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

        populateSearchResultsProductsTable( filteredProducts);
    });




    // Populate search results table for users
    function populateSearchResultsUsersTable(searchData) {
        var searchResultsTable = $('#searchResultsTableUsers').DataTable({
            data: searchData,
            columns: [
                { data: 'username' },
                { data: 'email' },
                { data: null, orderable: false, render: function(data) {
                        return '<button class="btn btn-outline-secondary">&minus;</button>';
                    }}
            ],
            searching: false,
            paging: false,
            info: false
        });
    }

// Populate search results table for products
    function populateSearchResultsProductsTable(searchData) {
        var searchResultsTable = $('#searchResultsTableProducts').DataTable({
            data: searchData,
            columns: [
                { data: 'productname' },
                { data: 'seller' },
                { data: 'price', render: function(data) {
                        var formattedPrice = parseFloat(data).toFixed(2);
                        var displayPrice = formattedPrice.substring(0, 4);
                        return displayPrice;
                    }},
                { data: null, orderable: false, render: function(data) {
                        return '<button class="btn btn-outline-secondary">&minus;</button>';
                    }}
            ],
            searching: false,
            paging: false,
            info: false
        });
    }

});
