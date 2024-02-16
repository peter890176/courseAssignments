#include <iostream>
#include <ctime>
#include <stack>
#include <fstream>
#include <vector>
#include <queue>
using namespace std;
#define SIZE 19

// CELL STRUCTURE
struct Cell
{
    bool visited;
    bool top_wall;
    bool bot_wall;
    bool left_wall;
    bool right_wall;
    char display;
};
// FUNCTION DECLARATIONS
void Initialize(Cell Maze[][SIZE]);
void GenerateMaze(Cell Maze[][SIZE], int& posX, int& posY, int& goalX, int& goalY);
void Redraw(Cell Maze[][SIZE]);

// MAIN
int main() {
    Cell Maze[SIZE][SIZE];
    int posX = 0;
    int posY = 0;
    int goalX = 0;
    int goalY = 0;
    bool game_over = false;
    int start_x = 0;
    int start_y = 0;
    int end_x = 0;
    int end_y = 0;

    vector<int> row;
    row.assign(SIZE, 1);
    vector<vector<int>>matrix;
    matrix.assign(SIZE, row);

    while (!game_over) {
        Initialize(Maze);
        /*Redraw(Maze);*/
        GenerateMaze(Maze, posX, posY, goalX, goalY);
        for (int i = 0; i < SIZE; i++)
        {
            cout << endl;
            for (int j = 0; j < SIZE; j++)
            {
                if (Maze[i][j].display == ' ') { matrix[i][j] = 0; }
                if (Maze[i][j].display == '*') { matrix[i][j] = 1; }
                if (Maze[i][j].display == 'S')
                {
                    matrix[i][j] = 2;
                    start_x = j;
                    start_y = i;
                }

                if (Maze[i][j].display == 'E')
                {
                    matrix[i][j] = 3;
                    end_x = j;
                    end_y = i;
                }

                cout << matrix[i][j];
            }
        }
        game_over = true;
        cout << endl;
        cout << "this done";
    }

    cout << endl;
    cout << start_y << endl;
    cout << start_x << endl;
    cout << end_y << endl;
    cout << end_x << endl;

    vector<int> row2;
    row2.assign(SIZE, 0);
    vector<vector<int>>visited;
    visited.assign(SIZE, row);
    for (int i = 0; i < SIZE; i++)
    {
        cout << endl;
        for (int j = 0; j < SIZE; j++)
        {
            if (matrix[i][j] == 0) { visited[i][j] = 0; }
            if (matrix[i][j] == 1) { visited[i][j] = 1; }
            if (matrix[i][j] == 2)
            {
                visited[i][j] = 1;
            }

            if (matrix[i][j] == 3)
            {
                visited[i][j] = 0;
            }
            cout << visited[i][j];
        }
    }

    int counter = 4;
    queue<pair<int, int>> q;
    q.push({start_y,start_x });
    while (!q.empty())
    {
        auto t = q.front();
        if (t.first == end_y && t.second == end_x)
        {
            cout << endl;
            cout << "meet the end";
            break;
        }
        q.pop();
        if (matrix[t.first + 1][t.second] != 1 && visited[t.first + 1][t.second] != 1)
        {
            q.push({ t.first + 1, t.second });
            matrix[t.first + 1][t.second] = counter;
            visited[t.first + 1][t.second] = 1;
            counter = counter + 1;
        }

        if (matrix[t.first][t.second + 1] != 1 && visited[t.first][t.second + 1] != 1)
        {
            q.push({ t.first , t.second + 1 });
            matrix[t.first][t.second + 1] = counter;
            visited[t.first][t.second + 1] = 1;
            counter = counter + 1;
        }

        if (matrix[t.first - 1][t.second] != 1 && visited[t.first - 1][t.second] != 1)
        {
            q.push({ t.first - 1, t.second });
            matrix[t.first - 1][t.second] = counter;
            visited[t.first - 1][t.second] = 1;
            counter = counter + 1;
        }

        if (matrix[t.first][t.second - 1] != 1 && visited[t.first][t.second - 1] != 1)
        {
            q.push({ t.first, t.second - 1 });
            matrix[t.first][t.second - 1] = counter;
            visited[t.first][t.second - 1] = 1;
            counter = counter + 1;
        }
    }
    cout << endl;
    for (int i = 0; i < SIZE; i++)
    {
        cout << endl;
        for (int j = 0; j < SIZE; j++)
        {
            cout << matrix[i][j];
        }
    }
    
    /*backtrack until matrix[][]==2(start)*/
    cout << endl;
    cout << matrix[end_y][end_x];
    queue<pair<int, int>> q2;
    q2.push({ end_y,end_x });
    while (!q2.empty()) 
    {
        auto t = q2.front();
        if (t.first == start_y && t.second == start_x)
        {
            cout << endl;
            cout << "meet the start";
            break;
        }
        q2.pop();

        if (matrix[t.first + 1][t.second] != 1 
            && matrix[t.first + 1][t.second]< matrix[t.first][t.second]
            && visited[t.first + 1][t.second] != 0
            && matrix[t.first + 1][t.second] != -1)
        {
            q2.push({ t.first + 1, t.second });
            matrix[t.first][t.second] = -1;
        }

        if (matrix[t.first][t.second+1] != 1 
            && matrix[t.first][t.second + 1] < matrix[t.first][t.second] 
            && visited[t.first][t.second + 1] != 0
            && matrix[t.first][t.second + 1] != -1)
        {
            q2.push({ t.first , t.second + 1 });
            matrix[t.first][t.second] = -1;
        }

        if (matrix[t.first-1][t.second] != 1 
            && matrix[t.first - 1][t.second ] < matrix[t.first][t.second] 
            && visited[t.first - 1][t.second ] != 0
            && matrix[t.first - 1][t.second] != -1)
        {
            q2.push({ t.first - 1, t.second });
            matrix[t.first][t.second] = -1;
        }

        if (matrix[t.first ][t.second - 1] != 1 
            && matrix[t.first][t.second - 1] < matrix[t.first][t.second] 
            && visited[t.first ][t.second - 1] != 0
            && matrix[t.first][t.second - 1] != -1)
        {
            q2.push({ t.first, t.second - 1 });
            matrix[t.first][t.second] = -1;
        }
    }

    cout << endl;
    for (int i = 0; i < SIZE; i++)
    {
        cout << endl;
        for (int j = 0; j < SIZE; j++)
        {
            cout << matrix[i][j];
        }
    }

    vector<char> row3;
    row3.assign(SIZE, '*');
    vector<vector<char>>matrix_print;
    matrix_print.assign(SIZE, row3);
   
    cout << endl;
    for (int i = 0; i < SIZE; i++)
        
    {
        cout << endl;
        for (int j = 0; j < SIZE; j++)
        {
            if (Maze[i][j].display == '*') { matrix_print[i][j] = 'o'; }
            if (Maze[i][j].display == ' ') { matrix_print[i][j] = ' '; }
            if (Maze[i][j].display == 'S') { matrix_print[i][j] = 'S'; }
            if (matrix[i][j] == -1) { matrix_print[i][j] = '.'; }
            if (Maze[i][j].display == 'E') { matrix_print[i][j] = 'E'; }
            cout << " "<<matrix_print[i][j];
        }
    }
    return 0;
}

// INITIALIZE MAZE
void Initialize(Cell Maze[][SIZE]) {
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            Maze[i][j].display = '*';
            Maze[i][j].visited = false;
            Maze[i][j].top_wall = true;
            Maze[i][j].bot_wall = true;
            Maze[i][j].left_wall = true;
            Maze[i][j].right_wall = true;
        }
    }

    for (int i = 1; i < SIZE - 1; i++) {
        for (int j = 1; j < SIZE - 1; j++) {
            // Border Cells have fewer accessible walls
            Maze[1][j].top_wall = false;
            Maze[SIZE - 2][j].bot_wall = false;
            Maze[i][1].left_wall = false;
            Maze[i][SIZE - 2].right_wall = false;
        }
    }
}

// REDRAW MAZE
void Redraw(Cell Maze[][SIZE]) {
    for (int i = 0; i < SIZE; i++) {
        cout << endl;
        for (int j = 0; j < SIZE; j++)
            cout << " " << Maze[i][j].display;
    }
}

// GENERATE MAZE
void GenerateMaze(Cell Maze[][SIZE], int& posX, int& posY, int& goalX, int& goalY) {
    srand((unsigned)time(NULL));                                            // Pick random start cell
    int random = 0;
    int randomX = ((2 * rand()) + 1) % (SIZE - 1);                      // Generate a random odd number between 1 and SIZE
    int randomY = ((2 * rand()) + 1) % (SIZE - 1);                      // Generate a random odd number between 1 and SIZE
    posX = randomX;                                             // Save player's initial X position
    posY = randomY;                                             // Save player's initial Y position
    int visitedCells = 1;
    int totalCells = ((SIZE - 1) / 2) * ((SIZE - 1) / 2);
    int percent = 0;
    stack<int> back_trackX, back_trackY;                        // Stack is used to trace the reverse path

    Maze[randomY][randomX].display = 'S';                      // Set S as the start cell
    Maze[randomY][randomX].visited = true;                     // Set start cell as visited;

    while (visitedCells < totalCells)
    {
        if (((Maze[randomY - 2][randomX].visited == false) && (Maze[randomY][randomX].top_wall == true && Maze[randomY - 2][randomX].bot_wall == true)) ||
            ((Maze[randomY + 2][randomX].visited == false) && (Maze[randomY][randomX].bot_wall == true && Maze[randomY + 2][randomX].top_wall == true)) ||
            ((Maze[randomY][randomX - 2].visited == false) && (Maze[randomY][randomX].left_wall == true && Maze[randomY][randomX - 2].right_wall == true)) ||
            ((Maze[randomY][randomX + 2].visited == false) && (Maze[randomY][randomX].right_wall == true && Maze[randomY][randomX + 2].left_wall == true)))
        {
            random = (rand() % 4) + 1;      // Pick a random wall 1-4 to knock down

             // GO UP
            if ((random == 1) && (randomY > 1)) {
                if (Maze[randomY - 2][randomX].visited == false) {    // If not visited
                    Maze[randomY - 1][randomX].display = ' ';    // Delete display
                    Maze[randomY - 1][randomX].visited = true;   // Mark cell as visited
                    Maze[randomY][randomX].top_wall = false;   // Knock down wall

                    back_trackX.push(randomX);          // Push X for back track
                    back_trackY.push(randomY);          // Push Y for back track

                    randomY -= 2;                   // Move to next cell
                    Maze[randomY][randomX].visited = true;     // Mark cell moved to as visited
                    Maze[randomY][randomX].display = ' ';      // Update path
                    Maze[randomY][randomX].bot_wall = false;   // Knock down wall
                    visitedCells++;                 // Increase visitedCells counter
                }
                else
                    continue;
            }

            // GO DOWN
            else if ((random == 2) && (randomY < SIZE - 2)) {
                if (Maze[randomY + 2][randomX].visited == false) {    // If not visited
                    Maze[randomY + 1][randomX].display = ' ';    // Delete display
                    Maze[randomY + 1][randomX].visited = true;   // Mark cell as visited
                    Maze[randomY][randomX].bot_wall = false;   // Knock down wall

                    back_trackX.push(randomX);          // Push X for back track
                    back_trackY.push(randomY);          // Push Y for back track

                    randomY += 2;                   // Move to next cell
                    Maze[randomY][randomX].visited = true;     // Mark cell moved to as visited
                    Maze[randomY][randomX].display = ' ';      // Update path
                    Maze[randomY][randomX].top_wall = false;   // Knock down wall
                    visitedCells++;                 // Increase visitedCells counter
                }
                else
                    continue;
            }

            // GO LEFT
            else if ((random == 3) && (randomX > 1)) {
                if (Maze[randomY][randomX - 2].visited == false) {    // If not visited
                    Maze[randomY][randomX - 1].display = ' ';    // Delete display
                    Maze[randomY][randomX - 1].visited = true;   // Mark cell as visited
                    Maze[randomY][randomX].left_wall = false;  // Knock down wall

                    back_trackX.push(randomX);          // Push X for back track
                    back_trackY.push(randomY);          // Push Y for back track

                    randomX -= 2;                   // Move to next cell
                    Maze[randomY][randomX].visited = true;     // Mark cell moved to as visited
                    Maze[randomY][randomX].display = ' ';      // Update path
                    Maze[randomY][randomX].right_wall = false; // Knock down wall
                    visitedCells++;                 // Increase visitedCells counter
                }
                else
                    continue;
            }

            // GO RIGHT
            else if ((random == 4) && (randomX < SIZE - 2)) {
                if (Maze[randomY][randomX + 2].visited == false) {    // If not visited
                    Maze[randomY][randomX + 1].display = ' ';    // Delete display
                    Maze[randomY][randomX + 1].visited = true;   // Mark cell as visited
                    Maze[randomY][randomX].right_wall = false; // Knock down wall

                    back_trackX.push(randomX);          // Push X for back track
                    back_trackY.push(randomY);          // Push Y for back track

                    randomX += 2;                   // Move to next cell
                    Maze[randomY][randomX].visited = true;     // Mark cell moved to as visited
                    Maze[randomY][randomX].display = ' ';      // Update path
                    Maze[randomY][randomX].left_wall = false;  // Knock down wall
                    visitedCells++;                 // Increase visitedCells counter
                }
                else
                    continue;
            }

        }
        else {
            randomX = back_trackX.top();
            back_trackX.pop();

            randomY = back_trackY.top();
            back_trackY.pop();
        }

        /*Redraw(Maze);*/
    }

    goalX = randomX;
    goalY = randomY;
    Maze[goalY][goalX].display = 'E';
    Redraw(Maze);
    cout << endl << "\a\t   Complete!" << endl;
}



