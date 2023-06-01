@RestController
@RequestMapping("/lists")
public class PlaylistController {
    @Autowired
    private PlaylistRepository playlistRepository;
    
    @PostMapping
    public ResponseEntity<?> adicionarPlaylist(@RequestBody Playlist playlist) {
        if (playlist.getNome() == null) {
            return ResponseEntity.badRequest().body("Nome da playlist não válido");
        }
        
        Playlist savedPlaylist = playlistRepository.save(playlist);
        return ResponseEntity.created(URI.create("/lists/" + savedPlaylist.getNome())).body(savedPlaylist);
    }
    
    @GetMapping
    public List<Playlist> obterTodasPlaylists() {
        return playlistRepository.findAll();
    }
    
    @GetMapping("/{listName}")
    public ResponseEntity<?> obterPlaylistPorNome(@PathVariable("listName") String nome) {
        Optional<Playlist> playlist = playlistRepository.findByNome(nome);
        
        if (playlist.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(playlist.get());
    }
    
    @DeleteMapping("/{listName}")
    public ResponseEntity<?> apagarPlaylistPorNome(@PathVariable("listName") String nome) {
        Optional<Playlist> playlist = playlistRepository.findByNome(nome);
        
        if (playlist.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        playlistRepository.delete(playlist.get());
        return ResponseEntity.noContent().build();
    }
}

@RestController
@RequestMapping("/songs")
public class MusicaController {
    @Autowired
    private MusicaRepository musicaRepository;
    
    // implementar métodos para adicionar, obter e apagar músicas
}
