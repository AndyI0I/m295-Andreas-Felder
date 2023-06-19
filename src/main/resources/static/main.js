$(document).ready(function() {
    //initialize the javascript
    //====================================================================================================
    var usersData = [];
    var User ={};
    var purchaseMade = [];
    var productsData = [];
    var productUsersData = [];
    var productUsersTable;
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
            usersTable = populateUsersTable("#usersTable", usersData);
        })
        .catch(error => {
            console.error('Error fetching users:', error);
        });

    // Fetch all products
    fetch('http://localhost:8080/products')
        .then(response => response.json())
        .then(data => {
            productsData = data;
            productsTable = populateProductsTable("#productsTable", productsData);
        })
        .catch(error => {
            console.error('Error fetching products:', error);
        });

    // Populate users table

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
                if (response.ok) {
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
                if (response.ok) {
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

    //======================================================================================================================
    // HELPER FUNCTIONS
    //======================================================================================================================

    function showSearchResults(table, searchData) {
        table.clear();
        table.rows.add(searchData);
        table.draw();
    }

    function refreshProductList() {
        productsTable.clear().rows.add(productsData).draw();
    }

    function populateUsersTable(id, data) {
        let table = $(id).DataTable({
            data: data,
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
        return table;
    }

    function populateProductsTable(id, data) {
        let table = $(id).DataTable({
            data: data,
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
        return table;
    }

    function populateProductUsersTable(id, data) {
        // Generate the table dynamically
        var tableHtml = '<table id="productUsersTable" class="table table-striped">';
        tableHtml += '<thead><tr><th>Username</th><th>Email</th></tr></thead>';
        tableHtml += '<tbody>';

        data.forEach(function(user) {
            tableHtml += '<tr>';
            tableHtml += '<td>' + user.username + '</td>';
            tableHtml += '<td>' + user.email + '</td>';
            tableHtml += '</tr>';
        });

        tableHtml += '</tbody></table>';

        // Set the table HTML content
        $(id).html(tableHtml);

        // Initialize the DataTable
        var table = $('#productUsersTable').DataTable({
            searching: false,
            paging: false,
            info: false
        });

        return table;
    }

    function populatePurchaseTable(id, data){
        var tableHtml = '<table id="purchaseTable" class="table table-striped">';
        tableHtml += '<thead><tr><th>Purchase ID</th><th>Products bought</th> <th>Total Cost</th> <th>Pending</th></tr></thead>';
        tableHtml += '<tbody>';


        data.forEach(function(purchase) {
            tableHtml += '<tr>';
            tableHtml += '<td>' + purchase.PurchaseId + '</td>';
            tableHtml += '<td>' + purchase.ProductsBought + '</td>';
            tableHtml += '<td>' + parseFloat(purchase.TotalCost).toFixed(2)+ '</td>';
            tableHtml += '<td>' + purchase.Pending + '</td>';
            tableHtml += '</tr>';
        });

        tableHtml += '</tbody></table>';

        // Set the table HTML content
        $(id).html(tableHtml);

        // Initialize the DataTable
        var table = $('#productUsersTable').DataTable({
            searching: false,
            paging: false,
            info: false
        });

        return table;
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

    //======================================================================================================================
    // MODAL FUNCTIONALITY
    //======================================================================================================================

    // Function to open the mini popup window for updating a product
    function openUpdateProductModal(product) {
        // Fetch the existing product details
        $('#updateProductNameInput').val(product.productname);
        $('#updateSellerInput').val(product.seller);
        $('#updatePriceInput').val(product.price);

        // Show the mini popup window
        $('#updateProductModal').modal('show');

        $('#updateProductButton').on('click', function() {
            // Create a new product object with updated details
            var updatedProduct = {
                productname: $('#updateProductNameInput').val(),
                seller: $('#updateSellerInput').val(),
                price: parseFloat($('#updatePriceInput').val()),
            };

            // Send the PUT request to update the product
            fetch('http://localhost:8080/products/' + product.id, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(updatedProduct),
            })
                .then(response => {
                    if (response.ok) {
                        alert('Product updated successfully.');

                        // Update the productsData array with the updated product
                        var index = productsData.findIndex(p => p.id === product.id);
                        if (index !== -1) {
                            productsData[index] = updatedProduct;
                        }

                        // Refresh the product list or update the UI accordingly
                        refreshProductList();
                    } else {
                        alert('Failed to update product.');
                    }
                })
                .catch(error => {
                    console.error('Error updating product:', error);
                    alert('An error occurred while updating the product.');
                });
        });
    }

    function confirmDeleteProduct(product) {
        // Show confirmation dialog
        var confirmation = confirm('Are you sure you want to delete this product?');

        if (confirmation) {
            // Send the DELETE request to remove the product
            fetch('http://localhost:8080/products/' + product.id, {
                method: 'DELETE',
            })
                .then(response => {
                    if (response.ok) {
                        alert('Product deleted successfully.');

                        // Remove the product from the productsData array
                        var index = productsData.findIndex(p => p.id === product.id);
                        if (index !== -1) {
                            productsData.splice(index, 1);
                        }

                        // Refresh the product list or update the UI accordingly
                        refreshProductList();
                    } else {
                        alert('Failed to delete product.');
                    }
                })
                .catch(error => {
                    console.error('Error deleting product:', error);
                    alert('An error occurred while deleting the product.');
                });
        }
    }

    $('#productsTable, #searchResultsTableProducts').on('click', '.btn-outline-secondary', function() {
        var buttonId = $(this).attr('id');
        var productId = buttonId.split('-')[1];

        // Get the product details using the productId
        var product = getProductById(productId);

        // Populate the modal with the product details
        populateModal(product);

        // Show the modal
        $('#detailsModal').modal('show');
    });

    $('#usersTable, #searchResultsTableUsers').on('click', '.btn-outline-secondary', function() {
        var buttonId = $(this).attr('id');
        var userId = buttonId.split('-')[1];

        // Get the user details using the userId
        var user = getUserById(userId);

        // Populate the modal with the user details
        populateModal(user);

        // Show the modal
        $('#detailsModal').modal('show');
    });

    // Function to get product details by id
    function getProductById(productId) {
        var products = productsData.filter(function(product) {
            return product.id === parseInt(productId);
        });
        if (products.length > 0) {
            return products[0];
        }
        return null;
    }

    // Function to get user details by id
    function getUserById(userId) {
        var users = usersData.filter(function(user) {
            return user.id === parseInt(userId);
        });

        if (users.length > 0) {
            return users[0];
        }

        return null;
    }

    function openUpdateUserModal(user) {
        // Fetch the existing user details
        $('#updateUsernameInput').val(user.username);
        $('#updateEmailInput').val(user.email);

        // Show the mini popup window
        $('#updateUserModal').modal('show');

        $('#updateUserButton').on('click', function() {
            // Create a new user object with updated details
            var updatedUser = {
                username: $('#updateUsernameInput').val(),
                email: $('#updateEmailInput').val(),
            };

            // Send the PUT request to update the user
            fetch('http://localhost:8080/user/' + user.id, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(updatedUser),
            })
                .then(response => {
                    if (response.ok) {
                        alert('User updated successfully.');

                        // Update the usersData array with the updated user
                        var index = usersData.findIndex(u => u.id === user.id);
                        if (index !== -1) {
                            usersData[index] = updatedUser;
                        }

                        // Refresh the user list or update the UI accordingly
                        refreshUserList();
                    } else {
                        alert('Failed to update user.');
                    }
                })
                .catch(error => {
                    console.error('Error updating user:', error);
                    alert('An error occurred while updating the user.');
                });
        });
    }

    // Function to refresh the user list
    function refreshUserList() {
        usersTable.clear().rows.add(usersData).draw();
    }


    function confirmDeleteUser(user) {
        // Show confirmation dialog
        var confirmation = confirm('Are you sure you want to delete this user?');

        if (confirmation) {
            // Send the DELETE request to remove the user
            fetch('http://localhost:8080/user/' + user.id, {
                method: 'DELETE',
            })
                .then(response => {
                    if (response.ok) {
                        alert('User deleted successfully.');

                        // Remove the user from the usersData array
                        var index = usersData.findIndex(u => u.id === user.id);
                        if (index !== -1) {
                            usersData.splice(index, 1);
                        }

                        // Refresh the user list or update the UI accordingly
                        refreshUserList();
                    } else {
                        alert('Failed to delete user.');
                    }
                })
                .catch(error => {
                    console.error('Error deleting user:', error);
                    alert('An error occurred while deleting the user.');
                });
        }
    }

    // Function to populate the modal with data
    function populateModal(data) {
        var modalTitle = $('#detailsModalTitle');
        var modalBody = $('#detailsModalBody');
        var editMenu = $('#editMenu');

        if (data) {
            modalTitle.text(data.productname || data.username);
            modalBody.empty();
            editMenu.empty();

            // Customize the modal body content based on the data
            if (data.productname) {
                modalBody.append('<p><strong>Seller:</strong> ' + data.seller + '</p>');
                modalBody.append('<p><strong>Price:</strong> ' + data.price.toFixed(2) + '</p>');
                // Load DataTable for product users
                var productId = data.id;
                var productUsersUrl = 'http://localhost:8080/product/' + productId + '/user';

                // Fetch product users data
                fetch(productUsersUrl)
                    .then(response => response.json())
                    .then(data => {
                        productUsersData = data;
                        productUsersTable = populateProductUsersTable("#modalTableContent", data);
                    })
                    .catch(error => {
                        console.error('Error fetching product users:', error);
                    });

                // Add buttons for product editing
                editMenu.append('<button id="editProductButton" class="btn btn-primary">Update Details</button>');
                editMenu.append('<button id="removeProductButton" class="btn btn-danger">Remove Product</button>');

                // Add click event listeners for the buttons
                $('#editProductButton').click(function() {
                    openUpdateProductModal(data);
                });

                $('#removeProductButton').click(function() {
                    confirmDeleteProduct(data);
                });
            } else if (data.username) {
                modalBody.append('<p><strong>Email:</strong> ' + data.email + '</p>');

                // Add buttons for user editing
                editMenu.append('<button id="editUserButton" class="btn btn-primary">Update Details</button>');
                editMenu.append('<button id="removeUserButton" class="btn btn-danger">Remove User</button>');

                var userDataUrl = "http://localhost:8080/user/" + data.id;
                purchaseMade = [];
                fetch(userDataUrl)
                    .then(response => response.json())
                    .then(data => {
                        User = data;

                        User.purchaseHistory.forEach(purchase => {
                            let productsBought = 0;
                            let totalCost = 0;

                            purchase.Products.forEach(product => {
                                productsBought += product.quantity;
                                totalCost += product.price * product.quantity;
                            })

                            let purchaseRecord = {
                                PurchaseId: purchase.id,
                                ProductsBought: productsBought,
                                TotalCost: totalCost,
                                Pending: purchase.isPending
                            }
                            purchaseMade.push(purchaseRecord);
                        });

                        productUsersTable = populatePurchaseTable("#modalTableContent", purchaseMade);
                    })
                    .catch(error => {
                        console.error('Error fetching product users:', error);
                    });

                // Add click event listeners for the buttons
                $('#editUserButton').click(function() {
                    openUpdateUserModal(data);
                });

                $('#removeUserButton').click(function() {
                    confirmDeleteUser(data);
                });
            }
        }
    }
});
